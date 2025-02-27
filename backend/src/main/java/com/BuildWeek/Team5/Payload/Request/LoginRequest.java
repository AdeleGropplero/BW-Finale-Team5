package com.BuildWeek.Team5.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class LoginRequest {

    @NotBlank(message = "Il campo username risulta vuoto")
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank(message = "Il campo password risulta vuoto")
    @Size(min = 3, max = 20)
    private String password;
}
