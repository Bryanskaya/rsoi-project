package ru.bmstu.identityprovider.repository;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class BaseRepository {
    @Resource
    public WebClient webClient;
}
