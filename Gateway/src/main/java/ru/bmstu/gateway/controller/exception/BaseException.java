package ru.bmstu.gateway.controller.exception;

import org.springframework.http.HttpStatusCode;

public class BaseException extends RuntimeException {
    public static String message = "Exception was caught";
    public int code;

    public BaseException(String msg, HttpStatusCode codeStatus) {
        super(msg);
        message = msg;
        code = codeStatus.value();
    }
}