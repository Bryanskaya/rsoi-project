package ru.bmstu.identityprovider.dto.converter;

import ru.bmstu.identityprovider.dto.AuthRequest;
import ru.bmstu.identityprovider.dto.TokenRequest;


public class AuthConverter {
    public static TokenRequest fromAuthRequestToTokenRequest(AuthRequest request) {
        return new TokenRequest()
                .setUsername(request.userName)
                .setPassword(request.password);
    }
}
