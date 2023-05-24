package ru.bmstu.gateway.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.util.UUID;

@Data
@Validated
@Accessors(chain = true)
public class CreateReservationRequest {
    private UUID hotelUid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public boolean isValid() {
        return hotelUid != null && startDate != null && endDate != null;
    }
}
