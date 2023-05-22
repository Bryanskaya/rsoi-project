package ru.bmstu.gateway.dto.converter;

import ru.bmstu.gateway.dto.response.LoyaltyInfoResponse;
import ru.bmstu.gateway.dto.response.ReservationResponse;
import ru.bmstu.gateway.dto.response.UserInfoResponse;

import java.util.ArrayList;

public class UserInfoResponseConverter {
    public static UserInfoResponse createUserInfoResponse(ArrayList<ReservationResponse> reservationResponses,
                                                          LoyaltyInfoResponse loyaltyInfoResponse) {
        return new UserInfoResponse()
                .setReservations(reservationResponses)
                .setLoyalty(loyaltyInfoResponse);
    }
}
