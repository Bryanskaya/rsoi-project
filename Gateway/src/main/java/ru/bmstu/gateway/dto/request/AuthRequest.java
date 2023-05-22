package ru.bmstu.gateway.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class AuthRequest {
    @JsonProperty("username")
    public String userName;

    @JsonProperty("password")
    public String password;
}
