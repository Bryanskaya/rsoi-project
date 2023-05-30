package ru.bmstu.gateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleResponse {
    @JsonProperty(value = "role")
    private String role;
}
