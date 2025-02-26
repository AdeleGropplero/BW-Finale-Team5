package com.BuildWeek.Team5.Payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IndirizzoDTO {

    @NotBlank(message = "Il campo via non può essere vuoto")
    private String via;

    @NotNull(message = "Il campo civico non può essere vuoto")
    private int civico;

    private String provincia;

    private String cap;  //ho cambiato da int a String vediamo se da errori

    @NotBlank(message = "Il campo comune non può essere vuoto")
    private String comune;

    @Override
    public String toString() {
        return "\n{" + '\n' +
                "via = " + via + '\n' +
                "civico = " + civico + '\n' +
                "provincia = " + provincia + '\n' +
                "cap = " + cap + '\n' +
                "comune = " + comune + '\n' +
                '}';
    }
}
