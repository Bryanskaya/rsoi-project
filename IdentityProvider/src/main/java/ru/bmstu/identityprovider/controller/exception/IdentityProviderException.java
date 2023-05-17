package ru.bmstu.identityprovider.controller.exception;

public class IdentityProviderException extends RuntimeException {
    public static String MSG = "Identity provider unavailable %s";

    public IdentityProviderException(String msg) {
        super(String.format(MSG, msg));
    }
}
