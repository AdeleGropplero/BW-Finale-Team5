package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Enum.TipoRuolo;
import com.BuildWeek.Team5.Exception.EmailDuplicated;
import com.BuildWeek.Team5.Exception.UsernameDuplicated;
import com.BuildWeek.Team5.Model.Ruolo;
import com.BuildWeek.Team5.Model.Utente;
import com.BuildWeek.Team5.Payload.Request.RegistrationRequest;
import com.BuildWeek.Team5.Payload.UtenteDTO;
import com.BuildWeek.Team5.Repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class UtenteService {

    // proprietà admin user
    @Value("${user.admin.username}")
    private String adminUsername;

    @Value("${user.admin.email}")
    private String adminEmail;

    @Value("${user.admin.password}")
    private String adminPassword;

    @Value("${user.admin.nome}")
    private String adminNome;

    @Value("${user.admin.cognome}")
    private String adminCognome;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // metodo per creare admin
    public void createAdmin(){

        // verifico se l'admin è già presente nel database
        if(utenteRepository.existsByUsername(adminUsername)){
            return;
        }

        Utente adminUser = new Utente(adminUsername, adminEmail, passwordEncoder.encode(adminPassword), adminNome, adminCognome);
        adminUser.setRuolo(new Ruolo(TipoRuolo.ROLE_ADMIN));
        utenteRepository.save(adminUser);
    }

    public String salvaUtente(RegistrationRequest utenteRequest){
        checkDuplicatedKey(utenteRequest.getUsername(), utenteRequest.getEmail());

        String encodedPassword = passwordEncoder.encode(utenteRequest.getPassword());

        Utente utente = new Utente(
                utenteRequest.getUsername(),
                utenteRequest.getEmail(),
                encodedPassword,
                utenteRequest.getNome(),
                utenteRequest.getCognome()
        );

        // ❗❗ al momento tutti gli utenti si stanno registrando come user
        utente.setRuolo(new Ruolo(TipoRuolo.ROLE_USER));
        utenteRepository.save(utente);
        return "L'utente " + utente.getUsername() + " con id " + utente.getId() + " è stato salvato correttamente";
    }

    // verificare eventuali campi duplicati --> la gestione try/catch affidata al controller
    public void checkDuplicatedKey(String username, String email) {

        if (utenteRepository.existsByUsername(username)) {
            throw new UsernameDuplicated("L'username inserito è già stato utilizzato");
        }

        if (utenteRepository.existsByEmail(email)) {
            throw new EmailDuplicated("L'email inserita è già stata utilizzata");
        }
    }

    // DTO -> ENTITY
    public Utente dto_entity(UtenteDTO utenteDTO){
        Utente utente = new Utente();

        utente.setUsername(utenteDTO.getUsername());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setAvatar(utenteDTO.getAvatar());

        return utente;
    }

    // ENTITY -> DTO
    public UtenteDTO entity_dto(Utente utente){
        UtenteDTO utenteDTO = new UtenteDTO();

        utenteDTO.setUsername(utente.getUsername());
        utenteDTO.setEmail(utente.getEmail());
        utenteDTO.setPassword(utente.getPassword());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setCognome(utente.getCognome());
        utenteDTO.setAvatar(utente.getAvatar());

        return utenteDTO;
    }
}
