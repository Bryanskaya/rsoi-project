package ru.bmstu.gateway.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.bmstu.gateway.dto.enums.logStatuses.ActionType;

import java.util.Date;
import java.util.UUID;


@Data
@Accessors(chain = true)
public class LogInfoDTO {
    @JsonProperty("eventUuid")
    public UUID eventUuid;

    @JsonProperty("eventDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date eventDate;

    @JsonProperty("user")
    public String user;

    @JsonProperty("action")
    public ActionType action;

    @JsonProperty("requestData")
    public Object requestData;

    @JsonProperty("service")
    public String service = "GATEWAY";


    public LogInfoDTO(String user, ActionType action, Object requestData) {
        this.eventUuid = UUID.randomUUID();
        this.eventDate = new Date();
        this.user = user;
        this.action = action;
        this.requestData = requestData;
    }
}
