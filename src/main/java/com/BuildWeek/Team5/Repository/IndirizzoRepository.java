package com.BuildWeek.Team5.Repository;

import com.BuildWeek.Team5.Model.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {

    Optional<Indirizzo> findFirstByComune(String comune);
}
