package ru.bmstu.statisticsapp.kafka.dao.exception;

public class SelectException  extends RuntimeException {
    public SelectException(String eMsg, String causeMsg) {
        super(String.format("[STATISTICS]: Select error: %s, cause: %s.", eMsg, causeMsg));
    }
}
