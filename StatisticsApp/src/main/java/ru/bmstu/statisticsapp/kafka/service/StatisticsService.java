package ru.bmstu.statisticsapp.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bmstu.statisticsapp.kafka.dao.StatisticsDao;
import ru.bmstu.statisticsapp.kafka.models.Message;
import ru.bmstu.statisticsapp.kafka.models.QueryServiceAvg;
import ru.bmstu.statisticsapp.kafka.models.ServiceAvg;

import java.util.List;


@Slf4j
@Service
public class StatisticsService {
    @Autowired
    private StatisticsDao dao;

    public void process(Message msg) {
        dao.insert(msg);
    }

    public List<Message> select() {
        return dao.selectAll();
    }

    public List<ServiceAvg> selectServiceAvgTime() {
        return dao.selectServiceAvgTime();
    }

    public List<QueryServiceAvg> selectQueryAvgTime() {
        return dao.selectQueryAvgTime();
    }
}