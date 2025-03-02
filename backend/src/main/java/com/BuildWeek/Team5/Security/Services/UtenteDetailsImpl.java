package com.BuildWeek.Team5.Security.Services;

import com.BuildWeek.Team5.Model.Ruolo;
import com.BuildWeek.Team5.Model.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor

public class UtenteDetailsImpl implements UserDetails{

    // personalizzazione dei dettagli da inserire nel token JWT

    private Long id;
    private String username;
    private String email;

    // la password verrà ignorata quando verrà generato il token
    @JsonIgnore
    private String password;

    // Spring Security si aspetta una collection di autorità
    private Collection<? extends GrantedAuthority> ruoli;

    public static UtenteDetailsImpl costruisciDettagli(Utente utente){

        // conversione del ruolo in granted authoruty per poter essere riconosciuto da Spring Security
        GrantedAuthority ruoloGranted = new SimpleGrantedAuthority(utente.getRuolo().getTipoRuolo().name());
        return new UtenteDetailsImpl(utente.getId(), utente.getUsername(), utente.getEmail(), utente.getPassword(), List.of(ruoloGranted));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruoli;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
