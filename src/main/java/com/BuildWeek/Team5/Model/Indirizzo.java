package com.BuildWeek.Team5.Model;

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

    private int civico;

    @Column(nullable = false)
    private String localita; // Sigla provincia, Regione

    @Column(nullable = false)
    private String cap; //ho cambiato da int a String vediamo se da errori

    private String comune;

    public Indirizzo(String via, int civico,  String comune) {
        this.via = via;
        this.civico = civico;
        this.comune = comune;
    }
}
