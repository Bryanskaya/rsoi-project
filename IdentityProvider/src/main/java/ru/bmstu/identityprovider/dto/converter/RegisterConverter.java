package ru.bmstu.identityprovider.dto.converter;

import ru.bmstu.identityprovider.dto.Credentials;
import ru.bmstu.identityprovider.dto.ProfileDto;
import ru.bmstu.identityprovider.dto.RegisterRequest;
import ru.bmstu.identityprovider.dto.RegisterRequestDto;

public class RegisterConverter {
    public static RegisterRequestDto fromRegisterRequestToRegisterRequestDto(RegisterRequest request) {
        return new RegisterRequestDto()
                .setCredentials(new Credentials()
                        .setPassword(request.credentials.password))
                .setProfile(new ProfileDto()
                        .setFirstName(request.profile.firstName)
                        .setLastName(request.profile.lastName)
                        .setEmail(request.profile.email)
                        .setLogin(request.profile.login)
                        .setMobilePhone(request.profile.mobilePhone));

    }
}
