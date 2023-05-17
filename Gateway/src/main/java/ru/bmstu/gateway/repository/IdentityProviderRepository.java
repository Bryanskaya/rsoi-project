package ru.bmstu.gateway.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import ru.bmstu.gateway.dto.RegisterRequest;

@Slf4j
@Repository
public class IdentityProviderRepository extends BaseRepository {
    public HttpStatusCode register(RegisterRequest request) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .host(appParams.identityProvider)
                        .path(appParams.pathIdentityProvider + "/register")
                        .port(appParams.portIdentityProvider)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(request))
                .exchangeToMono(clientResponse ->
                        Mono.just(clientResponse.statusCode()))
                .block();
    }
}
