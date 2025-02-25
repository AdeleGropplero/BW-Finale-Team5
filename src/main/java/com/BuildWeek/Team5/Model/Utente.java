package com.BuildWeek.Team5.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nome;

    private String cognome;

    //private String avatar;


    public Utente(String username, String email, String password, String nome, String cognome) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Utente{ \n" +
                "id = " + id + ", \n" +
                "username = " + username + ", \n"  +
                "email = " + email + ", \n" +
                "password = " + password + ", \n" +
                "nome = " + nome + ", \n" +
                "cognome = " + cognome + ", \n" +
                '}';
    }
}
