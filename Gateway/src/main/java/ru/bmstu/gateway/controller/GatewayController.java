package ru.bmstu.gateway.controller;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.gateway.controller.exception.data.RequestDataErrorException;
import ru.bmstu.gateway.dto.*;
import ru.bmstu.gateway.dto.enums.logStatuses.ActionType;
import ru.bmstu.gateway.kafka.KafkaProducer;
import ru.bmstu.gateway.service.GatewayService;
import ru.bmstu.gateway.service.TokenService;

import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private TokenService tokenService;

    private final KafkaProducer producer;


//    @GetMapping(value = "/callback")
//    public ResponseEntity<?> callback(Principal principal){
//        return ResponseEntity
//                .ok("Hello, world!");
//    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        log.info(">>> GATEWAY: registration request was caught.");
        producer.send(new LogInfoDTO(null, ActionType.REGISTRATION, request));

        return new ResponseEntity(gatewayService.register(request));
    }

    @PostMapping(value = "/authorize")
    public TokenResponse authorize(@RequestBody AuthRequest request) {
        log.info(">>> GATEWAY: authorization request was caught.");
        producer.send(new LogInfoDTO(request.userName, ActionType.AUTHORIZATION, request));

        return gatewayService.getToken(request);
    }

    @GetMapping(value = "/hotels", produces = "application/json")
    public ResponseEntity<?> getHotels(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                       @PathParam(value = "page") Integer page,
                                       @PathParam(value = "size") Integer size) {
        log.info(">>> GATEWAY: Request to get all hotels was caught (params: page={}, size={}).", page, size);
        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), ActionType.ALL_HOTELS, null));

        tokenService.validateToken(bearerToken);
        return gatewayService.getHotels(page, size);
    }

    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<?> getUserInfo(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info(">>> GATEWAY: Request to get all reservations by current username was caught.");
        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), ActionType.RESERVATIONS_BY_USER, null));

        tokenService.validateToken(bearerToken);
        return ResponseEntity
                .ok()
                .body(gatewayService.getUserInfo(bearerToken));
    }

    @GetMapping(value = "/reservations", produces = "application/json")
    public ResponseEntity<?> getReservationsByUsername(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info(">>> GATEWAY: Request to get all reservations by username was caught.");
        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), ActionType.RESERVATIONS_BY_USERNAME, null));

        tokenService.validateToken(bearerToken);
        return ResponseEntity
                .ok()
                .body(gatewayService.getReservationsList(bearerToken));
    }

    @GetMapping(value = "/reservations/{reservationUid}", produces = "application/json")
    public ResponseEntity<?> getReservationByUsernameReservationUid(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                                                    @PathVariable(value = "reservationUid") UUID reservationUid) {
        log.info(">>> GATEWAY: Request to get all reservations by username and reservationUid={} was caught.", reservationUid);
        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), ActionType.RESERVATION_BY_USERNAME_RESERVATIONUID, null));

        tokenService.validateToken(bearerToken);
        return ResponseEntity
                .ok()
                .body(gatewayService.getReservationByUsernameReservationUid(bearerToken, reservationUid));
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity<?> postReservation(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                             @RequestBody CreateReservationRequest request) {
        log.info(">>> GATEWAY: Request to create reservation was caught (data={}).", request.toString());
        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), ActionType.CREATE_RESERVATION, request));

        tokenService.validateToken(bearerToken);

        if (!request.isValid())
            throw new RequestDataErrorException(request.toString());

        return ResponseEntity
                .ok()
                .body(gatewayService.postReservation(bearerToken, request));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/reservations/{reservationUid}", produces = "application/json")
    public void cancelReservation(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                  @PathVariable(value = "reservationUid") UUID reservationUid) {
        log.info(">>> GATEWAY: Request to delete reservation was caught (reservationUid={}).", reservationUid);
        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), ActionType.DELETE_RESERVATION, null));

        tokenService.validateToken(bearerToken);

        gatewayService.cancelReservation(bearerToken, reservationUid);
    }

    @GetMapping(value = "/loyalty", produces = "application/json")
    public ResponseEntity<?> getLoyaltyInfoResponseByUsername(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info(">>> GATEWAY: Request to get loyalty info was caught.");
        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), ActionType.LOYALTY_INFO_BY_USERNAME, null));

        tokenService.validateToken(bearerToken);

        return ResponseEntity
                .ok()
                .body(gatewayService.getLoyaltyInfoResponseByUsername(bearerToken));
    }
}
