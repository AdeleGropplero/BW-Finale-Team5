package com.BuildWeek.Team5.Payload.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class JwtResponse {

    private String username;
    private long id;
    private String email;
    private List<String> ruolo;
    private String token;
    private String type = "Bearer ";

    public JwtResponse(String username, long id, String email, List<String> ruolo, String token) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.ruolo = ruolo;
        this.token = token;
    }
}
