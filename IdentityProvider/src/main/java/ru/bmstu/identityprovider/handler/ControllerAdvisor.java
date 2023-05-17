package ru.bmstu.identityprovider.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.bmstu.identityprovider.controller.exception.BaseException;
import ru.bmstu.identityprovider.controller.exception.OktaNotAvailableException;


@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler({
            OktaNotAvailableException.class
    })
    public ResponseEntity<?> handleUnavailableException(BaseException e) {
        Error err = new Error()
                .setCode(e.code)
                .setMessage(e.getMessage());

        return ResponseEntity
                .status(e.code)
                .body(err);
    }
}
