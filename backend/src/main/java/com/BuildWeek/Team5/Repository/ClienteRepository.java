package com.BuildWeek.Team5.Repository;

import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Model.Fattura;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByPartitaIva(String partitaIva);
    boolean existsByEmail( String email );

    List<Cliente> findByFatturatoAnnualeGreaterThan(double fatturatoAnnuale);

    List<Cliente> findByDataInserimentoBefore(LocalDate dataInserimento);

    List<Cliente> findByDataUltimoContattoBefore(LocalDate dataUltimoContatto);

    List<Cliente> findByNomeContattoContaining(String nomeContatto);

   @Query ("SELECT c FROM Cliente c JOIN c.indirizzi i " +
            "WHERE i.sede = 'LEGALE' ORDER BY i.provincia ASC" )
    List<Cliente> findByProvinciaSedeLegale();

}
