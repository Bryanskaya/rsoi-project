package ru.bmstu.gateway.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import ru.bmstu.gateway.dto.enums.ReservationStatus;

@Data
@Validated
@Accessors(chain = true)
public class PaymentInfo {
    private ReservationStatus status;
    private Integer price;
}
