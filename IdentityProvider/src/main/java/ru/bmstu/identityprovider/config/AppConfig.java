package ru.bmstu.identityprovider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AppConfig {
    @Autowired
    private AppParams appParams;

    @Bean
    public WebClient webClient()  {
        return WebClient
//                .builder()
//                .baseUrl(appParams.identityProviderUrl)
//                .clientConnector(new ReactorClientHttpConnector(
//                        HttpClient.create().wiretap(true)
//                ))
//                .build();
                .create(appParams.url);
    }
}
