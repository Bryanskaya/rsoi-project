package ru.bmstu.gateway.controller.exception.data.token;

import org.springframework.http.HttpStatusCode;
import ru.bmstu.gateway.controller.exception.BaseException;

public class UnauthorizedException extends BaseException {
    public static String message = "User unauthorized";

    public UnauthorizedException(HttpStatusCode codeStatus) {
        super(message, codeStatus);
    }
}