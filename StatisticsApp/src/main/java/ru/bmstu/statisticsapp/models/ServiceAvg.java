package ru.bmstu.statisticsapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Duration;

public class ServiceAvg {
    @JsonProperty("service")
    public String service;

    @JsonProperty("num")
    public BigInteger num;

    @JsonProperty("avg_time")
    public Double avgTime;
}
