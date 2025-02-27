package com.BuildWeek.Team5.Security.JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component

public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // settiamo il formato di ritorno verso il client
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // settare lo status della risposta
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // contenuto del json in caso di errore
        final Map<String, Object> infoErrori = new HashMap<>();
        infoErrori.put("stato", HttpServletResponse.SC_UNAUTHORIZED);
        infoErrori.put("errore", "Autorizzazione non valida");
        infoErrori.put("messaggio", authException.getMessage());
        infoErrori.put("path", request.getServletPath());

        // convertiamo gli oggetti in formato JSON
        final ObjectMapper mappingErrori = new ObjectMapper();
        mappingErrori.writeValue(response.getOutputStream(), infoErrori);
    }
}
