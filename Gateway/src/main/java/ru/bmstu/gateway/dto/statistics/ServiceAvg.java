package ru.bmstu.gateway.dto.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class ServiceAvg {
    @JsonProperty("service")
    public String service;

    @JsonProperty("num")
    public BigInteger num;

    @JsonProperty("avg_time")
    public Double avgTime;
}
