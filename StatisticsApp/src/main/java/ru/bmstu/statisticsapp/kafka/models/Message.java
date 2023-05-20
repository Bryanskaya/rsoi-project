package ru.bmstu.statisticsapp.kafka.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.bmstu.statisticsapp.kafka.models.enums.ActionType;

import java.util.Date;
import java.util.UUID;

@Data
@Accessors(chain = true)
@ToString
public class Message {
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
    public String service;
}

