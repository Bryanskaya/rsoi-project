package ru.bmstu.gateway.controller.exception.service;

import org.springframework.http.HttpStatusCode;
import ru.bmstu.gateway.controller.exception.BaseException;

public class IdentityProviderNotAvailableException  extends BaseException {
    public static String message = "Identity Provider Service unavailable";

    public IdentityProviderNotAvailableException(HttpStatusCode codeStatus) {
        super(message, codeStatus);
    }
}