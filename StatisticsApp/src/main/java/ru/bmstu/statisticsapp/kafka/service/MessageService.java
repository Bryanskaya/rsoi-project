package ru.bmstu.statisticsapp.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bmstu.statisticsapp.kafka.dao.StatisticsDao;
import ru.bmstu.statisticsapp.kafka.models.Message;


@Slf4j
@Service
public class MessageService {
    @Autowired
    private StatisticsDao dao;

    public void process(Message msg) {
        dao.insert(msg);
    }
}
