package com.BuildWeek.Team5.Controller;

import com.BuildWeek.Team5.Exception.EmailDuplicated;
import com.BuildWeek.Team5.Exception.UsernameDuplicated;
import com.BuildWeek.Team5.Payload.Request.LoginRequest;
import com.BuildWeek.Team5.Payload.Request.RegistrationRequest;
import com.BuildWeek.Team5.Payload.Response.JwtResponse;
import com.BuildWeek.Team5.Security.JWT.JwtUtils;
import com.BuildWeek.Team5.Security.Services.UtenteDetailsImpl;
import com.BuildWeek.Team5.Service.UtenteService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/utente")

public class UtenteController {

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    UtenteService utenteService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazioneUtente(@RequestPart("utente") @Validated RegistrationRequest nuovoUtente, BindingResult validation){

        if(validation.hasErrors()){
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for(ObjectError error : validation.getAllErrors()){
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

        try{

            // invio immagine al servizio Cloudinary
            //Map mappaUpload = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());

            // indirizzo dell'immagine
            //String urlImage = mappaUpload.get("secure_url").toString();

            // set che setta la nuova immagine
            //nuovoUtente.setAvatar(urlImage);

           String salvaUtenteMessaggio = utenteService.salvaUtente(nuovoUtente);

            return new ResponseEntity<>(salvaUtenteMessaggio, HttpStatus.CREATED);

        } catch (EmailDuplicated | UsernameDuplicated e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest, BindingResult validation){

        if(validation.hasErrors()){
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for(ObjectError error : validation.getAllErrors()){
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

        try {

            // creazione del token per l'autenticazione
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // impostare l'autenticazione nel contesto di sicurezza Spring
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generazione del token JWT
            String token = jwtUtils.createJwtToken(authentication);

            // Recupero le info dell'utente
            UtenteDetailsImpl utenteDetails = (UtenteDetailsImpl) authentication.getPrincipal();
            List<String> webRuoli = utenteDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .toList();

            // Creazione della risposta con il token JWT

            JwtResponse jwtResponse = new JwtResponse(
                    utenteDetails.getUsername(),
                    utenteDetails.getId(),
                    utenteDetails.getEmail(),
                    webRuoli,
                    token
            );

            // restituzione della risposta con il token
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (AuthenticationException ex){
            return new ResponseEntity<>("Credenziali non valide", HttpStatus.UNAUTHORIZED);
        }
    }
}
