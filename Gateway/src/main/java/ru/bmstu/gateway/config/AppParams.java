package ru.bmstu.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppParams {
    @Value(value = "${host.service.hotel:localhost}")
    public String hostHotel;
    @Value(value = "${host.service.loyalty:localhost}")
    public String hostLoyalty;
    @Value(value = "${host.service.payment:localhost}")
    public String hostPayment;

    @Value(value = "${host.service.identityProvider}")
    public String hostIdentityProvider;

    @Value(value = "${host.service.statistics}")
    public String hostStatistics;

    @Value(value = "${path.service.hotel}")
    public String pathHotel;
    @Value(value = "${path.service.loyalty}")
    public String pathLoyalty;
    @Value(value = "${path.service.reservation}")
    public String pathReservation;
    @Value(value = "${path.service.payment}")
    public String pathPayment;
    @Value(value = "${path.service.identityProvider}")
    public String pathIdentityProvider;
    @Value(value = "${path.service.statistics}")
    public String pathStatistics;

    @Value(value = "${port.service.hotel}")
    public String portHotel;
    @Value(value = "${port.service.loyalty}")
    public String portLoyalty;
    @Value(value = "${port.service.payment}")
    public String portPayment;
    @Value(value = "${port.service.identityProvider}")
    public String portIdentityProvider;
    @Value(value = "${port.service.statistics}")
    public String portStatistics;

    @Value(value = "${const.jwt.token.prefix}")
    public String jwtPrefix;
    @Value(value = "${const.jwt.modulus}")
    public String modulus;
    @Value(value = "${const.jwt.exponent}")
    public String exponent;
}
