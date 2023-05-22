package ru.bmstu.gateway.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.bmstu.gateway.dto.Credentials;
import ru.bmstu.gateway.dto.Profile;


@Data
@Accessors(chain = true)
public class RegisterRequest {
    @JsonProperty(value = "profile")
    public Profile profile;

    @JsonProperty(value = "credentials")
    public Credentials credentials;
}
