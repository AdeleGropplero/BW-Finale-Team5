package com.BuildWeek.Team5.Model;

import com.BuildWeek.Team5.Enum.StatoFattura;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="fatture")
@Data

public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    @Column(nullable = false)
    private LocalDate dataFattura;
    @Column(nullable = false)
    private double importo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;

}
