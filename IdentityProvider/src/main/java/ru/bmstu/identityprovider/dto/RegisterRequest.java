package ru.bmstu.identityprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
public class RegisterRequest {
    @JsonProperty(value = "profile")
    public Profile profile;

    @JsonProperty(value = "credentials")
    public Credentials credentials;
}
