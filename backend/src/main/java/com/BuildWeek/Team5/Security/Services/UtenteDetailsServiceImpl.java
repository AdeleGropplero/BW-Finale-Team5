package com.BuildWeek.Team5.Security.Services;

import com.BuildWeek.Team5.Model.Utente;
import com.BuildWeek.Team5.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UtenteDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // ricerca dell'utente tramite username
        Utente utente = utenteRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // restituzione di un oggetto UserDetails con le info dell'utente da includere nel token
        return UtenteDetailsImpl.costruisciDettagli(utente);
    }
}
