package com.BuildWeek.Team5.Repository;

import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Model.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente>  findByFatturatoAnnualeGreaterThan (double fatturatoAnnuale);
    List<Cliente> findByDataInserimentoBefore(LocalDate dataInserimento);
    List<Cliente> findByDataUltimoContattoBefore(LocalDate dataUltimoContatto);
    List<Cliente> findByNomeContattoContaining(String nomeContatto);
}
