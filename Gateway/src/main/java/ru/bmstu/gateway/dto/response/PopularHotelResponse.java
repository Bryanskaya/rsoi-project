package ru.bmstu.gateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Accessors(chain = true)
public class PopularHotelResponse {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("cnt")
    public Integer cnt;
}