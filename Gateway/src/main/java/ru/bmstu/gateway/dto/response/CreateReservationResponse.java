package ru.bmstu.gateway.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import ru.bmstu.gateway.dto.PaymentInfo;
import ru.bmstu.gateway.dto.enums.ReservationStatus;

import java.sql.Date;
import java.util.UUID;

@Data
@Validated
@Accessors(chain = true)
public class CreateReservationResponse {
    private UUID reservationUid;
    private UUID hotelUid;
    private Date startDate;
    private Date endDate;
    private Integer discount;
    private ReservationStatus status;
    private PaymentInfo payment;
}
