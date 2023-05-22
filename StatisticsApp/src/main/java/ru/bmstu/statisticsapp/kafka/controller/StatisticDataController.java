package ru.bmstu.statisticsapp.kafka.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.statisticsapp.kafka.service.StatisticsService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistics")
public class StatisticDataController {
    private final StatisticsService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll() {
        log.info("[STATISTICS]: Request to get all activity info was caught.");

        return ResponseEntity
                .ok()
                .body(service.select());
    }

    @GetMapping(produces = "application/json", value = "/services/avgtime")
    public ResponseEntity<?> getServiceAvgTime() {
        log.info("[STATISTICS]: Request to get average services' processing time was caught.");

        return ResponseEntity
                .ok()
                .body(service.selectServiceAvgTime());
    }

    @GetMapping(produces = "application/json", value = "/queries/avgtime")
    public ResponseEntity<?> getQueryAvgTime() {
        log.info("[STATISTICS]: Request to get average queries' processing time was caught.");

        return ResponseEntity
                .ok()
                .body(service.selectQueryAvgTime());
    }
}
