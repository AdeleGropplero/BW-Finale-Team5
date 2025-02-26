package com.BuildWeek.Team5.Repository;

import com.BuildWeek.Team5.Model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail( String email );
}
