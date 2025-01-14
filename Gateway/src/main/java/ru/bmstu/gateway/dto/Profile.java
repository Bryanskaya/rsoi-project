package ru.bmstu.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Profile {
    @JsonProperty(value = "firstName")
    public String firstName;

    @JsonProperty(value = "lastName")
    public String lastName;

    @JsonProperty(value = "email")
    public String email;

    @JsonProperty(value = "login")
    public String login;

    @JsonProperty(value = "mobilePhone")
    public String mobilePhone;

    public String toString() {
        return "Profile [firstname=" + firstName +
                ", lastname=" + lastName +
                ", email=" + email +
                ", login=" + login +
                ", mobilePhone=" + mobilePhone +
                "]";
    }
}
