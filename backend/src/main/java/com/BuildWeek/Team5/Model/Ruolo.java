package com.BuildWeek.Team5.Model;

import com.BuildWeek.Team5.Enum.TipoRuolo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Embeddable
@Data

public class Ruolo {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRuolo tipoRuolo;

    public Ruolo(){}

    public Ruolo(TipoRuolo tipoRuolo){
        this.tipoRuolo = tipoRuolo;
    }
}
