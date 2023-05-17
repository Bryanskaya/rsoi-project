package ru.bmstu.identityprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthRequest {
    @JsonProperty("username")
    public String userName;

    @JsonProperty("password")
    public String password;
}
