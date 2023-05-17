package ru.bmstu.identityprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenRequest {
    @JsonProperty(value = "scope")
    public String scope;

    @JsonProperty(value = "grant_type")
    public String grantType;

    @JsonProperty(value = "username")
    public String username;

    @JsonProperty(value = "password")
    public String password;

    @JsonProperty(value = "client_id")
    public String clientId;

    @JsonProperty(value = "client_secret")
    public String clientSecret;
}
