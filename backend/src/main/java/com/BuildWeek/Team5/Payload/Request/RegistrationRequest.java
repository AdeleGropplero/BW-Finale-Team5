package com.BuildWeek.Team5.Payload.Request;

import com.BuildWeek.Team5.Model.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data

public class RegistrationRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 20)
    private String cognome;

    @NotBlank
    @Email(message = "Email non valida")
    @Size(min = 3, max = 30)
    private String email;

    private Ruolo ruolo;

    @URL
    private String avatar;

}
