package ru.bmstu.identityprovider.controller.exception;

import org.springframework.http.HttpStatusCode;

public class OktaNotAvailableException extends BaseException {
    public static String message = "Okta Service unavailable %s";

    public OktaNotAvailableException(HttpStatusCode codeStatus, String msg) {
        super(String.format(message, msg), codeStatus);
    }
}
