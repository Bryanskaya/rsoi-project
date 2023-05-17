package ru.bmstu.gateway.controller.exception.service;

import org.springframework.http.HttpStatusCode;
import ru.bmstu.gateway.controller.exception.BaseException;

public class ReservationServiceNotAvailableException extends BaseException {
    public static String message = "Hotel Service unavailable";

    public ReservationServiceNotAvailableException(HttpStatusCode codeStatus) {
        super(message, codeStatus);
    }
}
