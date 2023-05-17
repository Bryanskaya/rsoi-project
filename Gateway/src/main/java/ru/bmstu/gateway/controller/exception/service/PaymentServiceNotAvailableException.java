package ru.bmstu.gateway.controller.exception.service;

import org.springframework.http.HttpStatusCode;
import ru.bmstu.gateway.controller.exception.BaseException;

public class PaymentServiceNotAvailableException extends BaseException {
    public static String message = "Payment Service unavailable";

    public PaymentServiceNotAvailableException(HttpStatusCode codeStatus) {
        super(message, codeStatus);
    }
}