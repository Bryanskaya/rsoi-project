package ru.bmstu.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Credentials {
    @JsonProperty(value = "password")
    public Password password;

    public static class Password {
        @JsonProperty(value = "value")
        public String value;
    }

    public String toString() {
        return "Credentials [<hidden>]";
    }
}