package ru.bmstu.identityprovider.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import ru.bmstu.identityprovider.config.AppParams;
import ru.bmstu.identityprovider.dto.*;
import ru.bmstu.identityprovider.dto.converter.AuthConverter;
import ru.bmstu.identityprovider.dto.converter.RegisterConverter;
import ru.bmstu.identityprovider.repository.IDRepository;

import java.util.ArrayList;

@Slf4j
@Service
public class IDService {
    @Autowired
    private AppParams appParams;

    @Autowired
    private IDRepository repository;


    public TokenResponse getToken(AuthRequest request) {
        TokenRequest tokenRequest = AuthConverter.fromAuthRequestToTokenRequest(request)
                .setScope(appParams.scope)
                .setGrantType(appParams.grantType)
                .setClientId(appParams.clientId)
                .setClientSecret(appParams.clientSecret);
        log.info(">>> Token request: {}", tokenRequest);

        TokenResponse response = repository.postToken(tokenRequest);
        log.info(">>> Token response: {}", response);

        return response;
    }

    public HttpStatusCode register(RegisterRequest request) {
        RegisterRequestDto requestDto = RegisterConverter.fromRegisterRequestToRegisterRequestDto(request);
        requestDto.setGroupIds(new ArrayList<>(){{
            add(appParams.groupId);
        }});

        return repository.register(requestDto);
    }
}
