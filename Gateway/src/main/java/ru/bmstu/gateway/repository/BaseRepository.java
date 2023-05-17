package ru.bmstu.gateway.repository;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bmstu.gateway.config.AppParams;

@Repository
public class BaseRepository {
    @Resource
    public WebClient webClient;

    @Autowired
    public AppParams appParams;
}
