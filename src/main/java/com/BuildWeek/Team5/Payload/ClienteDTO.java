package com.BuildWeek.Team5.Payload;

import com.BuildWeek.Team5.Enum.RagioneSociale;
import com.BuildWeek.Team5.Model.Fattura;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

@Data
public class ClienteDTO {
    @Enumerated(EnumType.STRING)
    private RagioneSociale ragioneSociale;

    @NotBlank(message = "Il campo PartitaIva non può essere vuoto")
    private String partitaIva;

    @NotBlank(message = "Il campo Email non può essere vuoto")
    @Email(message = "L'email non è valida")
    private String email;

    @NotNull(message = "Il campo Data è obbligatorio")
    private LocalDate dataInserimento;

    @NotNull(message = "Il campo Data è obbligatorio")
    private LocalDate dataUltimoContatto;

    private double fatturatoAnnuale;

    @NotBlank(message = "Il campo PEC non può essere vuoto")
    @Email(message = "Pec non valida!")
    private String pec;

    private String telefono;

    @Email(message = "Email contatto non valida!")
    private String emailContatto;

    @NotBlank(message = "Il campo Nome contatto non può essere vuoto")
    private String nomeContatto;

    @NotBlank(message = "Il campo cognome contatto non può essere vuoto")
    private String cognomeContatto;

    private String telefonoContatto;

    @URL
    private String logoAziendale;

    private Set<FatturaDTO> fatture;

    private Set<IndirizzoDTO> indirizzi;

    public ClienteDTO() {
        //generazione randomica della ragione sociale del client
        this.ragioneSociale = RagioneSociale.values()[new Random().nextInt(RagioneSociale.values().length)];
    }

    @Override
    public String toString() {
        return "\n{" +
                "ragioneSociale = " + ragioneSociale + '\n' +
                "partitaIva = " + partitaIva + '\n' +
                "email = " + email + '\n' +
                "dataInserimento = " + dataInserimento + '\n' +
                "dataUltimoContatto = " + dataUltimoContatto + '\n' +
                "fatturatoAnnuale = " + fatturatoAnnuale + '\n' +
                "pec = " + pec + '\n' +
                "telefono = " + telefono + '\n' +
                "emailContatto = " + emailContatto + '\n' +
                "nomeContatto = " + nomeContatto + '\n' +
                "cognomeContatto = " + cognomeContatto + '\n' +
                "telefonoContatto = " + telefonoContatto + '\n' +
                "logoAziendale = " + logoAziendale + '\n' +
                '}';
    }
}
