package ru.bmstu.gateway.repository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import ru.bmstu.gateway.controller.exception.service.GatewayErrorException;
import ru.bmstu.gateway.controller.exception.service.StatisticsServiceNotAvailableException;
import ru.bmstu.gateway.dto.LogInfoDTO;
import ru.bmstu.gateway.dto.statistics.QueryServiceAvg;
import ru.bmstu.gateway.dto.statistics.ServiceAvg;


@Repository
public class StatisticsRepository extends BaseRepository {
    public LogInfoDTO[] getStatistics() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .host(appParams.hostStatistics)
                        .path(appParams.pathStatistics)
                        .port(appParams.portStatistics)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatusCode::isError, error -> {
                    throw new StatisticsServiceNotAvailableException(error.statusCode());
                })
                .bodyToMono(LogInfoDTO[].class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    public ServiceAvg[] getServiceAvgTime() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .host(appParams.hostStatistics)
                        .path(appParams.pathStatistics + "/services/avgtime")
                        .port(appParams.portStatistics)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatusCode::isError, error -> {
                    throw new StatisticsServiceNotAvailableException(error.statusCode());
                })
                .bodyToMono(ServiceAvg[].class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    public QueryServiceAvg[] getQueryAvgTime() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .host(appParams.hostStatistics)
                        .path(appParams.pathStatistics + "/queries/avgtime")
                        .port(appParams.portStatistics)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatusCode::isError, error -> {
                    throw new StatisticsServiceNotAvailableException(error.statusCode());
                })
                .bodyToMono(QueryServiceAvg[].class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }
}
