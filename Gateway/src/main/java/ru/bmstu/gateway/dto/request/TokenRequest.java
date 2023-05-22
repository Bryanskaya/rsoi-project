package ru.bmstu.gateway.dto.request;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class TokenRequest {
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String grant_type;
}
