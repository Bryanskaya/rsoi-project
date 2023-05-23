package ru.bmstu.statisticsapp.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.bmstu.statisticsapp.dao.exception.InsertException;
import ru.bmstu.statisticsapp.dao.exception.SelectException;
import ru.bmstu.statisticsapp.models.Message;
import ru.bmstu.statisticsapp.models.QueryServiceAvg;
import ru.bmstu.statisticsapp.models.ServiceAvg;
import ru.bmstu.statisticsapp.models.mappers.MessageRowMapper;
import ru.bmstu.statisticsapp.models.mappers.QueryAvgRowMapper;
import ru.bmstu.statisticsapp.models.mappers.ServiceAvgRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final ObjectMapper mapper = new ObjectMapper();
    private final MessageRowMapper messageRowMapper = new MessageRowMapper();
    private final ServiceAvgRowMapper serviceAvgRowMapper = new ServiceAvgRowMapper();
    private final QueryAvgRowMapper queryAvgRowMapper = new QueryAvgRowMapper();


    public void insert(Message msg) {
        String insertQuery = "INSERT INTO activity_details " +
                "(event_uuid, username, action, event_start, event_end, " +
                "params, service) " +
                "VALUES (:eventUuid, :username, :action, :eventStart, :eventEnd, " +
                ":params::json, :service);";

        try {
            Map<String, Object> paramMap = getInsertParams(msg);

            jdbcTemplate.update(insertQuery, paramMap);
        } catch (Exception ex) {
            throw new InsertException(ex.getMessage(), ex.getCause() == null ? "" : ex.getCause().getMessage());
        }
    }

    public List<Message> selectAll() {
        String selectQuery = "SELECT * " +
                "FROM activity_details;";

        try {
            return jdbcTemplate.query(selectQuery, messageRowMapper);
        } catch (Exception ex) {
            throw new SelectException(ex.getMessage(), ex.getCause() == null ? "" : ex.getCause().getMessage());
        }
    }

    public List<ServiceAvg> selectServiceAvgTime() {
        String selectQuery = "SELECT service, COUNT(*) as num, " +
                "EXTRACT(EPOCH FROM AVG(event_end - event_start)) * 1000 as avg_time " +
                "FROM activity_details " +
                "GROUP BY service " +
                "ORDER BY avg_time;";

        try {
            return jdbcTemplate.query(selectQuery, serviceAvgRowMapper);
        } catch (Exception ex) {
            throw new SelectException(ex.getMessage(), ex.getCause() == null ? "" : ex.getCause().getMessage());
        }
    }

    public List<QueryServiceAvg> selectQueryAvgTime() {
        String selectQuery = "SELECT service, action, COUNT(*) as num, " +
                "EXTRACT(EPOCH FROM AVG(event_end - event_start)) * 1000 as avg_time " +
                "FROM activity_details " +
                "GROUP BY service, action " +
                "ORDER BY avg_time;";

        try {
            return jdbcTemplate.query(selectQuery, queryAvgRowMapper);
        } catch (Exception ex) {
            throw new SelectException(ex.getMessage(), ex.getCause() == null ? "" : ex.getCause().getMessage());
        }
    }

    private Map<String, Object> getInsertParams(Message msg) throws JsonProcessingException {
        return new HashMap<>() {{
            put("eventUuid", msg.eventUuid);
            put("username", msg.username);
            put("action", msg.action);
            put("eventStart", msg.eventStart);
            put("eventEnd", msg.eventEnd);
            put("params", mapper.writeValueAsString(msg.params));
            put("service", msg.service);
        }};
    }
}
