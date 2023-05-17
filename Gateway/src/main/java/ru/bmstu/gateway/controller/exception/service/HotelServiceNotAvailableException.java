package ru.bmstu.gateway.controller.exception.service;


import org.springframework.http.HttpStatusCode;
import ru.bmstu.gateway.controller.exception.BaseException;

public class HotelServiceNotAvailableException extends BaseException {
    public static String message = "Hotel Service unavailable";

    public HotelServiceNotAvailableException(HttpStatusCode codeStatus) {
        super(message, codeStatus);
    }
}
