package ru.bmstu.gateway.controller.exception.service;

import org.springframework.http.HttpStatusCode;
import ru.bmstu.gateway.controller.exception.BaseException;


public class StatisticsServiceNotAvailableException extends BaseException {
    public static String message = "Statistics Service unavailable";

    public StatisticsServiceNotAvailableException(HttpStatusCode codeStatus) {
        super(message, codeStatus);
    }
}
