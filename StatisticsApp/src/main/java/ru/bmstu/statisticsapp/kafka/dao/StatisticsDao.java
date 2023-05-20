package ru.bmstu.statisticsapp.kafka.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.bmstu.statisticsapp.kafka.dao.exception.InsertException;
import ru.bmstu.statisticsapp.kafka.models.Message;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    private String insertQuery = "INSERT INTO activity_details " +
            "(event_uuid, username, action, event_date, details, " +
            "service) " +
            "VALUES (:eventUuid, :username, :action, :eventDate, :details::json, " +
            ":service);";

    public void insert(Message msg) {
        try {
            Map<String, Object> paramMap = getInsertParams(msg);
            log.info("{}", paramMap);

            jdbcTemplate.update(insertQuery, paramMap);
        } catch (Exception ex) {
            throw new InsertException(ex.getMessage(), ex.getCause() == null ? "" : ex.getCause().getMessage());
        }
    }

    private Map<String, Object> getInsertParams(Message msg) throws JsonProcessingException {
        return new HashMap<>() {{
            put("eventUuid", msg.eventUuid);
            put("username", msg.user);
            put("action", msg.action.name());
            put("eventDate", msg.eventDate);
            put("details", mapper.writeValueAsString(msg.requestData));
            put("service", msg.service);
        }};
    }
}
