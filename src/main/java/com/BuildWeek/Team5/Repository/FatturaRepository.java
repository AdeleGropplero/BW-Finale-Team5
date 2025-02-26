package com.BuildWeek.Team5.Repository;

import com.BuildWeek.Team5.Model.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    @Query("SELECT f FROM Fattura f WHERE f.id IN (SELECT fa.id FROM Cliente c JOIN c.fatture fa WHERE c.idCliente = :idCliente)")
    List<Fattura> findByIdCliente(@Param("idCliente") Long idCliente);
}
