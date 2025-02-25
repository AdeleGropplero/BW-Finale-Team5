package com.BuildWeek.Team5.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteDTO {

    @NotBlank(message = "Il campo username non può essere vuoto")
    private String username;

    @NotBlank(message = "Il campo email non può essere vuoto")
    @Email (message = "Inserire una mail valida perfavore")
    private String email;

    @NotBlank(message = "Il campo password non può essere vuoto")
    private String password;

    private String nome;

    private String cognome;

}
