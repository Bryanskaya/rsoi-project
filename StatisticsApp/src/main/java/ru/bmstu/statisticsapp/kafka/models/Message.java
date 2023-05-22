package ru.bmstu.statisticsapp.kafka.models;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.bmstu.statisticsapp.kafka.models.enums.ActionType;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
@Accessors(chain = true)
@ToString
public class Message {
    @JsonProperty("eventUuid")
    public UUID eventUuid;

    @JsonProperty("eventStart")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date eventStart;

    @JsonProperty("eventEnd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date eventEnd;

    @JsonProperty("username")
    public String username;

    @JsonProperty("action")
    public ActionType action;

    @JsonProperty("params")
    public Map<String, Object> params;

    @JsonAnySetter
    void setParams(String key, Object value) {
        params.put(key, value);
    }

    @JsonProperty("service")
    public String service;
}

