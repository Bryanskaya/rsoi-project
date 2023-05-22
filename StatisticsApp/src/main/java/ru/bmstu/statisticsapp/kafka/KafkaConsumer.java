package ru.bmstu.statisticsapp.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.bmstu.statisticsapp.models.Message;
import ru.bmstu.statisticsapp.service.StatisticsService;

import java.util.function.Consumer;

@Slf4j
@Component
public class KafkaConsumer {
    @Autowired
    private StatisticsService service;

    @Bean
    public Consumer<Message> consumer() {
        return data -> {
            log.info("[STATISTICS]: {} was got from topic", data);

            try {
                service.process(data);
            } catch (Exception ex) {
                log.error("[STATISTICS]: {} was caught error, err={}", data, ex.getMessage());
            }
        };
    }
}
