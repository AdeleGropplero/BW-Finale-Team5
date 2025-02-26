package com.BuildWeek.Team5.Repository;

import com.BuildWeek.Team5.Enum.StatoFattura;
import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Model.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    // filtriamo le fatture dalla subquery interna
    // facciamo un JOIN di Cliente (preso dall'id che passo come parametro) e Fattura --> c.fatture è il Set<Fattura>, fa è l'alias per tutte le fatture collegate a quel cliente
    @Query("SELECT f FROM Fattura f WHERE f.id IN (SELECT fa.id FROM Cliente c JOIN c.fatture fa WHERE c.idCliente = :idCliente)")
    List<Fattura> findByIdCliente(@Param("idCliente") Long idCliente);
    List<Fattura> findByStatoFattura(StatoFattura statoFattura);
    List<Fattura> findByDataFatturaBefore(LocalDate dataFattura);
    @Query("SELECT f FROM Fattura f WHERE YEAR(f.dataFattura) = :annoFattura")
    List<Fattura> findByDataFattura(@Param("annoFattura") int annoFattura);
    List<Fattura> findByImportoBetween(double importoMin, double importoMax);
}
