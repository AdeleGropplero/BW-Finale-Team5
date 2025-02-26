package com.BuildWeek.Team5.Model;

import com.BuildWeek.Team5.Enum.RagioneSociale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="clienti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Enumerated(EnumType.STRING)
    private RagioneSociale ragioneSociale;

    @Column(nullable = false, unique = true)
    private String partitaIva;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dataInserimento;

    @Column(nullable = false)
    private LocalDate dataUltimoContatto;

    private double fatturatoAnnuale;

    @Column(nullable = false)
    private String pec;

    private String telefono;

    @Column(nullable = false)
    private String emailContatto;

    @Column(nullable = false)
    private String nomeContatto;

    @Column(nullable = false)
    private String cognomeContatto;

    private String telefonoContatto;

    private String logoAziendale;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="idFattura")
    private Set<Fattura> fatture;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="idIndirizzo")
    private Set<Indirizzo> indirizzi;
}
