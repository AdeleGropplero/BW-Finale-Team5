package com.BuildWeek.Team5.Security.JWT;

import com.BuildWeek.Team5.Security.Services.UtenteDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UtenteDetailsServiceImpl utenteDetailsService;

    private String analizzaJwt(HttpServletRequest request){

        // recuperiamo dall'header il JWT
        String headerAuthorization = request.getHeader("Authorization");

        // controllare che vi sia del testo e che il valore inizi con "Bearer "
        if(StringUtils.hasText(headerAuthorization) && headerAuthorization.startsWith("Bearer ")){

            // recupero la sottostringa
            return headerAuthorization.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // recupero la sottostringa dopo "Bearer " oppure null
        String jwt = analizzaJwt(request);

        // se la richiesta presenta un JWT lo convalidiamo --> si richiama il metodo in JwtUtils
        if(jwt != null && jwtUtils.validazioneJwtToken(jwt)){

            // recupero l'username dal jwt
            String username = jwtUtils.getUsernameFromToken(jwt);

            // recupero UtenteDetails da username --> creazione di un oggetto authentication
            UserDetails utenteDetails = utenteDetailsService.loadUserByUsername(username);

            // creazione di un oggetto UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            utenteDetails,
                            null,
                            utenteDetails.getAuthorities()
                    );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // impostare lo userDetails corrente nel contesto di Security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
