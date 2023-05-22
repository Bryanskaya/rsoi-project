package ru.bmstu.gateway.dto.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class QueryServiceAvg {
    @JsonProperty("service")
    public String service;

    @JsonProperty("num")
    public BigInteger num;

    @JsonProperty("avg_time")
    public Double avgTime;

    @JsonProperty("action")
    public String action;
}
