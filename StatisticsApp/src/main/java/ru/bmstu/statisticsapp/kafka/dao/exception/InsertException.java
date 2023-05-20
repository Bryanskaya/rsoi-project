package ru.bmstu.statisticsapp.kafka.dao.exception;

public class InsertException extends RuntimeException {
    public InsertException(String eMsg, String causeMsg) {
        super(String.format("Insert error: %s, cause: %s.", eMsg, causeMsg));
    }
}
