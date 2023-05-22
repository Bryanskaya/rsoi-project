package ru.bmstu.statisticsapp.models.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.bmstu.statisticsapp.dao.exception.SelectException;
import ru.bmstu.statisticsapp.models.Message;
import ru.bmstu.statisticsapp.models.enums.ActionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;


public class MessageRowMapper implements RowMapper<Message> {
    private final ObjectMapper mapper = new ObjectMapper();

    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message msg = new Message();

        msg.eventUuid = UUID.fromString(rs.getString("event_uuid"));
        msg.username = rs.getString("username");
        msg.eventStart = rs.getTimestamp("event_start");
        msg.eventEnd = rs.getTimestamp("event_end");
        msg.action = ActionType.valueOf(rs.getString("action"));
        msg.service = rs.getString("service");
        try {
            msg.params = mapper.readValue(rs.getString("params"), Map.class);
        } catch (Exception ex) {
            throw new SelectException(ex.getMessage(), ex.getCause() == null ? "" : ex.getCause().getMessage());
        }

        return msg;
    }
}
