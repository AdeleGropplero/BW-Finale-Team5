package com.BuildWeek.Team5.Model;

import com.BuildWeek.Team5.Enum.TipoSede;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "indirizzi")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String via;

    @Column(nullable = false)
    private int civico;

    private String provincia; // Sigla provincia, Regione

    private String cap; //ho cambiato da int a String vediamo se da errori

    @Column(nullable = false)
    private String comune;

    @Enumerated(EnumType.STRING)
    private TipoSede sede;

    public Indirizzo(String via, int civico,  String comune, TipoSede sede) {
        this.via = via;
        this.civico = civico;
        this.comune = comune;
        this.sede = sede;
    }
}
