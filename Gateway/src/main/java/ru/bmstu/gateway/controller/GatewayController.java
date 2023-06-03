package ru.bmstu.gateway.controller;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.gateway.controller.exception.data.RequestDataErrorException;
import ru.bmstu.gateway.controller.exception.data.token.RolePermissionException;
import ru.bmstu.gateway.dto.*;
import ru.bmstu.gateway.dto.enums.Role;
import ru.bmstu.gateway.config.ActionType;
import ru.bmstu.gateway.dto.request.AuthRequest;
import ru.bmstu.gateway.dto.request.CreateReservationRequest;
import ru.bmstu.gateway.dto.request.RegisterRequest;
import ru.bmstu.gateway.dto.response.RoleResponse;
import ru.bmstu.gateway.dto.response.TokenResponse;
import ru.bmstu.gateway.kafka.KafkaProducer;
import ru.bmstu.gateway.service.GatewayService;
import ru.bmstu.gateway.service.TokenService;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        log.info("[GATEWAY]: registration request ({}) was caught.", request);
        Date startDate = new Date();

        ResponseEntity<?> res = new ResponseEntity(gatewayService.register(request));

        producer.send(new LogInfoDTO(null, startDate, new Date(), ActionType.REGISTRATION, request));

        return res;
    }

    @PostMapping(value = "/authorize")
    public TokenResponse authorize(@RequestBody AuthRequest request) {
        log.info("[GATEWAY]: authorization request ({}) was caught.", request);
        Date startDate = new Date();

        TokenResponse tokenResponse = gatewayService.getToken(request);

        producer.send(new LogInfoDTO(request.userName, startDate, new Date(), ActionType.AUTHORIZATION, request));

        return tokenResponse;
    }

    @GetMapping(value = "/hotels", produces = "application/json")
    public ResponseEntity<?> getHotels(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                       @PathParam(value = "page") Integer page,
                                       @PathParam(value = "size") Integer size) {
        log.info("[GATEWAY]: Request to get all hotels was caught (params: page={}, size={}).", page, size);
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        ResponseEntity<?> hotelArr = gatewayService.getHotels(page, size);

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(), ActionType.ALL_HOTELS,
                new HashMap<String, Integer>() {{
                    put("page", page);
                    put("size", size);
        }}));

        return hotelArr;
    }

    @GetMapping(value = "/hotels/{hotelUid}/image", produces = "application/json")
    public String getHotelImageByHotelUid(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                          @PathVariable UUID hotelUid) {
        log.info("[GATEWAY]: Request to get hotel's url image by hotelUid={} was caught.", hotelUid);
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        String hotelImageUrl = gatewayService.getHotelImageByHotelUid(hotelUid);

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(), ActionType.HOTEL_IMAGE,
                new HashMap<String, UUID>() {{
                    put("hotelUid", hotelUid);
                }}));

        return hotelImageUrl;
    }

    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<?> getUserInfo(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get all reservations by current username ({}) was caught.", tokenService.getUsername(bearerToken));
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        ResponseEntity<?> userInfo = ResponseEntity
                .ok()
                .body(gatewayService.getUserInfo(bearerToken));

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(), ActionType.RESERVATIONS_BY_USER, null));

        return userInfo;
    }

    @GetMapping(value = "/reservations", produces = "application/json")
    public ResponseEntity<?> getReservationsByUsername(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get all reservations by username was caught.");
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        ResponseEntity<?> reservationArr = ResponseEntity
                .ok()
                .body(gatewayService.getReservationsList(bearerToken));

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(), ActionType.RESERVATIONS_BY_USERNAME, null));

        return reservationArr;
    }

    @GetMapping(value = "/reservations/{reservationUid}", produces = "application/json")
    public ResponseEntity<?> getReservationByUsernameReservationUid(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                                                    @PathVariable(value = "reservationUid") UUID reservationUid) {
        log.info("[GATEWAY]: Request to get all reservations by username and reservationUid={} was caught.", reservationUid);
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        ResponseEntity<?> reservationArr = ResponseEntity
                .ok()
                .body(gatewayService.getReservationByUsernameReservationUid(bearerToken, reservationUid));

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.RESERVATION_BY_USERNAME_RESERVATIONUID, new HashMap<String, UUID>() {{
                    put("reservationUUID", reservationUid);
        }}));

        return reservationArr;
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity<?> postReservation(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                             @RequestBody CreateReservationRequest request) {
        log.info("[GATEWAY]: Request to create reservation was caught (request={}).", request.toString());
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        if (!request.isValid())
            throw new RequestDataErrorException(request.toString());

        ResponseEntity<?> reservation = ResponseEntity
                .ok()
                .body(gatewayService.postReservation(bearerToken, request));

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.CREATE_RESERVATION, request));

        return reservation;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/reservations/{reservationUid}", produces = "application/json")
    public void cancelReservation(@RequestHeader(value = "Authorization", required = false) String bearerToken,
                                  @PathVariable(value = "reservationUid") UUID reservationUid) {
        log.info("[GATEWAY]: Request to delete reservation was caught (reservationUid={}).", reservationUid);
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        gatewayService.cancelReservation(bearerToken, reservationUid);

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.DELETE_RESERVATION, new HashMap<String, UUID>() {{
                    put("reservationUUID", reservationUid);
        }}));
    }

    @GetMapping(value = "/loyalty", produces = "application/json")
    public ResponseEntity<?> getLoyaltyInfoResponseByUsername(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get loyalty (user={}) info was caught.", tokenService.getUsername(bearerToken));
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        ResponseEntity<?> loyaltyInfo = ResponseEntity
                .ok()
                .body(gatewayService.getLoyaltyInfoResponseByUsername(bearerToken));

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.LOYALTY_INFO_BY_USERNAME, null));

        return loyaltyInfo;
    }

    @GetMapping(value = "/statistics", produces = "application/json")
    public ResponseEntity<?> getStatistics(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get statistics info was caught.");
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        if (!Objects.equals(tokenService.getRole(bearerToken).toLowerCase(), Role.ADMIN.name().toLowerCase()))
            throw new RolePermissionException();

        ResponseEntity<?> statisticsInfo = ResponseEntity
                .ok()
                .body(gatewayService.getStatistics());

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.SYS_STATISTICS, null));

        return statisticsInfo;
    }

    @GetMapping(value = "/statistics/services/avgtime", produces = "application/json")
    public ResponseEntity<?> getStatisticsServiceAvgTime(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get average services' processing time was caught.");
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        if (!Objects.equals(tokenService.getRole(bearerToken).toLowerCase(), Role.ADMIN.name().toLowerCase()))
            throw new RolePermissionException();

        ResponseEntity<?> statisticsInfo = ResponseEntity
                .ok()
                .body(gatewayService.getServiceAvgTime());

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.SYS_STATISTICS, null));

        return statisticsInfo;
    }

    @GetMapping(value = "/statistics/queries/avgtime", produces = "application/json")
    public ResponseEntity<?> getStatisticsQueryAvgTime(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get average queries' processing time was caught.");
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        if (!Objects.equals(tokenService.getRole(bearerToken).toLowerCase(), Role.ADMIN.name().toLowerCase()))
            throw new RolePermissionException();

        ResponseEntity<?> statisticsInfo = ResponseEntity
                .ok()
                .body(gatewayService.getQueryAvgTime());

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.SYS_STATISTICS, null));

        return statisticsInfo;
    }

    @GetMapping(value = "/me/role", produces = "application/json")
    public RoleResponse getUserRole(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get user's role (user={}) was caught.", tokenService.getUsername(bearerToken));
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        String role = tokenService.getRole(bearerToken);

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(), ActionType.USER_ROLE, null));

        return new RoleResponse()
                .setRole(role);
    }

    @GetMapping(value = "/hotels/popular", produces = "application/json")
    public ResponseEntity<?> getPopularHotels(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("[GATEWAY]: Request to get 3 most popular hotels was caught.");
        Date startDate = new Date();

        tokenService.validateToken(bearerToken);

        if (!Objects.equals(tokenService.getRole(bearerToken).toLowerCase(), Role.ADMIN.name().toLowerCase()))
            throw new RolePermissionException();

        ResponseEntity<?> hotelArr = ResponseEntity
                .ok()
                .body(gatewayService.getPopularHotels());

        producer.send(new LogInfoDTO(tokenService.getUsername(bearerToken), startDate, new Date(),
                ActionType.SYS_STATISTICS, null));

        return hotelArr;
    }
}
