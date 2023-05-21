package ru.bmstu.gateway.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import ru.bmstu.gateway.dto.enums.UserStatus;

@Data
@Validated
@Accessors(chain = true)
public class LoyaltyInfoResponse {
    private UserStatus status;
    private Integer discount;
    private Integer reservationCount;
}
