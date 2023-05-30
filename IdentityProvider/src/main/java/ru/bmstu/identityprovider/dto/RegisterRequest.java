package ru.bmstu.identityprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@ToString
public class RegisterRequest {
    @JsonProperty(value = "profile")
    public Profile profile;

    @JsonProperty(value = "credentials")
    public Credentials credentials;
}
