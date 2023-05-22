package ru.bmstu.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.bmstu.gateway.controller.exception.BaseException;
import ru.bmstu.gateway.controller.exception.data.*;
import ru.bmstu.gateway.controller.exception.data.token.*;
import ru.bmstu.gateway.controller.exception.service.*;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler({
            HotelServiceNotAvailableException.class,
            LoyaltyServiceNotAvailableException.class,
            PaymentServiceNotAvailableException.class,
            ReservationServiceNotAvailableException.class,
            IdentityProviderNotAvailableException.class,
            StatisticsServiceNotAvailableException.class,
            UnauthorizedException.class,
            TokenExpiredException.class,
            JwtParsingException.class,
            JwtEmptyException.class,
            KeyFactoryErrorException.class})
    public ResponseEntity<?> handleHotelServiceNotAvailableException(BaseException ex) {
        Error err = new Error()
                .setCode(ex.code)
                .setMessage(ex.getMessage());

        return ResponseEntity
                .status(ex.code)
                .body(err);
    }

    @ExceptionHandler(GatewayErrorException.class)
    public ResponseEntity<?> handleGatewayErrorException(GatewayErrorException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Error err = new Error()
                .setCode(status.value())
                .setMessage(ex.getMessage());

        return ResponseEntity
                .status(status)
                .body(err);
    }

    @ExceptionHandler({
            ReservationByUsernameNotFoundException.class,
            RelatedDataNotFoundException.class,
            ReservationByUsernameReservationUidNotFoundException.class
    })
    public ResponseEntity<?> handleReservationByUsernameNotFoundException(ReservationByUsernameNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Error err = new Error()
                .setCode(status.value())
                .setMessage(ex.getMessage());

        return ResponseEntity
                .status(status)
                .body(err);
    }

    @ExceptionHandler(RequestDataErrorException.class)
    public ResponseEntity<?> handleRequestDataErrorException(RequestDataErrorException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error err = new Error()
                .setCode(status.value())
                .setMessage(ex.getMessage());

        return ResponseEntity
                .status(status)
                .body(err);
    }

    @ExceptionHandler(RolePermissionException.class)
    public ResponseEntity<?> handleRolePermissionException(RolePermissionException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        Error err = new Error()
                .setCode(status.value())
                .setMessage(ex.getMessage());

        return ResponseEntity
                .status(status)
                .body(err);
    }
}
