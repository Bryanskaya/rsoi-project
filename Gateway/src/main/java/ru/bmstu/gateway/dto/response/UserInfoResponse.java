package ru.bmstu.gateway.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import ru.bmstu.gateway.dto.response.LoyaltyInfoResponse;
import ru.bmstu.gateway.dto.response.ReservationResponse;

import java.util.ArrayList;

@Data
@Validated
@Accessors(chain = true)
public class UserInfoResponse {
    private ArrayList<ReservationResponse> reservations;
    private LoyaltyInfoResponse loyalty;
}
