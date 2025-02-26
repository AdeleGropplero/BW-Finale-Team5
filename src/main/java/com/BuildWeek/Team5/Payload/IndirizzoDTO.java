package com.BuildWeek.Team5.Payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IndirizzoDTO {

    @NotBlank(message = "Il campo via non può essere vuoto")
    private String via;

    private int civico;

    @NotBlank(message = "Il campo localita non può essere vuoto")
    private String localita;

    @NotNull(message = "Il campo cap non può essere vuoto")
    private int cap;

    private String comune;
}
