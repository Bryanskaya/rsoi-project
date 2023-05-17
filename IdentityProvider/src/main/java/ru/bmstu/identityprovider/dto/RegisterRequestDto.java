package ru.bmstu.identityprovider.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
public class RegisterRequestDto {
    @JsonProperty(value = "credentials")
    public Credentials credentials;

    @JsonProperty(value = "groupIds")
    public ArrayList<String> groupIds;

    @JsonProperty(value = "profile")
    public ProfileDto profile;
}

