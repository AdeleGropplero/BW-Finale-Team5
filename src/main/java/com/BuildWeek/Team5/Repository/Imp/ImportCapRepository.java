package com.BuildWeek.Team5.Repository.Imp;

import com.BuildWeek.Team5.Model.Imp.ImportCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImportCapRepository extends JpaRepository<ImportCap, Long> {
    Optional<ImportCap> findByCodiceIstat(String codiceIstat);

    Optional<ImportCap> findByCap(String cap);
}
