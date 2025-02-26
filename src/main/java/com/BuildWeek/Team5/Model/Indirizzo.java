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
    private String localita;

    @Column(nullable = false)
    private int cap;

    private String comune;

    public Indirizzo(String via, int civico, String localita, int cap, String comune) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
    }
}
