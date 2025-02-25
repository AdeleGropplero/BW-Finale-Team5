package com.BuildWeek.Team5.Payload;

import com.BuildWeek.Team5.Enum.StatoFattura;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Random;

@Data
public class FatturaDTO {
    @NotNull(message = "Data non valida!")
    private LocalDate dataFattura;
    @NotNull(message = "Campo Importo obbligatorio")
    private double importo;
    private StatoFattura statoFattura;

    public FatturaDTO() {
        this.statoFattura = StatoFattura.values()[new Random().nextInt(StatoFattura.values().length)];
    }
}
