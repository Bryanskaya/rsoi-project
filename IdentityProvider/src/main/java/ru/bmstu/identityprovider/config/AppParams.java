package ru.bmstu.identityprovider.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppParams {
    @Value(value = "${const.okta.url}")
    public String url;

    @Value(value = "${const.okta.path_token}")
    public String pathToken;

    @Value(value = "${const.okta.path_register}")
    public String pathRegister;

    @Value(value = "${const.okta.scope}")
    public String scope;

    @Value(value = "${const.okta.grant_type}")
    public String grantType;

    @Value(value = "${const.okta.client_id}")
    public String clientId;

    @Value(value = "${const.okta.client_secret}")
    public String clientSecret;

    @Value(value = "${const.okta.group_id}")
    public String groupId;

    @Value(value = "${const.okta.auth_token}")
    public String authToken;
}
