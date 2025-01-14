package ru.bmstu.identityprovider.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import ru.bmstu.identityprovider.config.AppParams;
import ru.bmstu.identityprovider.controller.exception.IdentityProviderException;
import ru.bmstu.identityprovider.controller.exception.OktaNotAvailableException;
import ru.bmstu.identityprovider.dto.RegisterRequestDto;
import ru.bmstu.identityprovider.dto.TokenRequest;
import ru.bmstu.identityprovider.dto.TokenResponse;

import java.util.Map;


@Repository
public class IDRepository extends BaseRepository {
    @Autowired
    private AppParams appParams;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TokenResponse postToken(TokenRequest request) {
        MultiValueMap valueMap = new LinkedMultiValueMap<String, Object>();
        Map<String, Object> fieldMap = objectMapper.convertValue(request, new TypeReference<>() {});
        valueMap.setAll(fieldMap);

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(appParams.pathToken)
                        .build())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(valueMap))
                .retrieve()
                .onStatus(HttpStatusCode::isError, error -> {
                    throw new OktaNotAvailableException(error.statusCode(), error.toString());
                })
                .bodyToMono(TokenResponse.class).log()
                .onErrorMap(Throwable.class, error -> {
                    throw new IdentityProviderException(error.getMessage());
                })
                .block();
    }

    public HttpStatusCode register(RegisterRequestDto request) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(appParams.pathRegister)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, "*/*")
                .header("Authorization", appParams.authToken)
                .body(BodyInserters.fromValue(request))
                .exchangeToMono(clientResponse ->
                        Mono.just(clientResponse.statusCode()))
                .block();
    }
}
