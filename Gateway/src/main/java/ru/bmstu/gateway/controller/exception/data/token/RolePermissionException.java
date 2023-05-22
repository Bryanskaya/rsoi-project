package ru.bmstu.gateway.controller.exception.data.token;

import org.springframework.http.HttpStatus;
import ru.bmstu.gateway.controller.exception.BaseException;

public class RolePermissionException extends BaseException {
    public static String message = "Role permission error";

    public RolePermissionException(){
        super(message, HttpStatus.FORBIDDEN);
    }

    public RolePermissionException(HttpStatus codeStatus) {
            super(message, codeStatus);
        }

    public RolePermissionException(String msg, HttpStatus codeStatus) {
            super(message + ": " + msg, codeStatus);
        }

}
