package com.BuildWeek.Team5.Model.Imp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comuni_province")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImportComuniProvince {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comune;

    private String provincia;

    private String regione;

    private String sigla;

    @Column(name = "codice_istat")
    private String codiceIstat;
}
