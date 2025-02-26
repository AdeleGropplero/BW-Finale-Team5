package com.BuildWeek.Team5.Repository;

import com.BuildWeek.Team5.Model.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    List<Fattura> findByIdCliente(Long idCliente);
}
