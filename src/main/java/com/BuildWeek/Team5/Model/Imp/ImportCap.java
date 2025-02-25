package com.BuildWeek.Team5.Model.Imp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "istat_cap")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImportCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cap;

    @Column(name = "codice_istat")
    private String codiceIstat;
}
