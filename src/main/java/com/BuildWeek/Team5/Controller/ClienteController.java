package com.BuildWeek.Team5.Controller;

import com.BuildWeek.Team5.Exception.ClienteNotFound;
import com.BuildWeek.Team5.Exception.IndirizzoNotFound;
import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Model.Fattura;
import com.BuildWeek.Team5.Model.Indirizzo;
import com.BuildWeek.Team5.Payload.ClienteDTO;
import com.BuildWeek.Team5.Payload.FatturaDTO;
import com.BuildWeek.Team5.Payload.IndirizzoDTO;
import com.BuildWeek.Team5.Repository.ClienteRepository;
import com.BuildWeek.Team5.Service.ClienteService;
import com.BuildWeek.Team5.Service.FatturaService;
import com.BuildWeek.Team5.Service.IndirizzoService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    FatturaService fatturaService;

    @Autowired
    IndirizzoService indirizzoService;

    @PostMapping("/nuovo")
    public ResponseEntity<String> nuovoCliente(@RequestPart("dto") @Validated ArrayList<ClienteDTO> clienteDTOArrayList, BindingResult validation, @RequestPart("logoAziendale") MultipartFile logoAziendale) throws IOException {
        if (validation.hasErrors()) {
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for (ObjectError error : validation.getAllErrors()) {
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            //invio immagine al servizio Cloudinary
            Map mappaUpload = cloudinary.uploader().upload(logoAziendale.getBytes(), ObjectUtils.emptyMap());
            // l'indirizzo dell'immagine
            String urlImage = mappaUpload.get("secure_url").toString();
            // set che setta la nuova immagine

            //ℹ️ℹ️ℹ️ tutti i clienti hanno lo stesso logo
            for (ClienteDTO singoloCliente : clienteDTOArrayList) {
                singoloCliente.setLogoAziendale(urlImage);
            }

            clienteService.leggiArrayClienti(clienteDTOArrayList);
            return new ResponseEntity<>("I clienti sono stati salvati correttamente!", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Errore!", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{clienteID}")
    public ResponseEntity<String> nuovaFattura(@RequestBody @Validated FatturaDTO fatturaDTO, BindingResult validation, @PathVariable Long clienteID) {
        if (validation.hasErrors()) {
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for (ObjectError error : validation.getAllErrors()) {
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Cliente cliente = clienteRepository.findById(clienteID).orElseThrow(() -> new ClienteNotFound("Cliente non trovato!"));
            Fattura fattura = fatturaService.fromFatturaDTOtoFattura(fatturaDTO);
            long idFattura = fatturaService.salvaFattura(fattura);
            cliente.getFatture().add(fattura);
            clienteService.salvaCliente(cliente);
            return new ResponseEntity<>("La fattura con ID " + idFattura + " è stata aggiunta con successo!", HttpStatus.CREATED);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Prova post Indirizzo.
    @PostMapping("/indirizzo")
    public ResponseEntity<String> inserisciIndirizzo(@Validated @RequestBody IndirizzoDTO indirizzoDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for (ObjectError error : validation.getAllErrors()) {
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            String indirizzo = indirizzoService.insertIndirizzo(indirizzoDTO);

            return new ResponseEntity<>("Indirizzo inserito: " + indirizzo, HttpStatus.CREATED);
        } catch (IndirizzoNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/batchIndirizzi")
    public ResponseEntity<String> inserisciIndirizzi(@Validated @RequestBody List<IndirizzoDTO> listaDto, BindingResult validation) {
        if (validation.hasErrors()) {
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for (ObjectError error : validation.getAllErrors()) {
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            String indirizziInseriti = indirizzoService.insertIndirizzi(listaDto);
            return new ResponseEntity<>(indirizziInseriti, HttpStatus.CREATED);
        } catch (IndirizzoNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Ordinamento liste clienti --------------------------------------------------

    @GetMapping("/clientiAZ")
    public ResponseEntity<String> getClientiAZ() {
        try {
            String clientiAZ = clienteService.clientiAZ();
            return new ResponseEntity<>(clientiAZ, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerFatturato")
    public ResponseEntity<String> clientiPerFatturato() {
        try {
            String clientiPerFatturato = clienteService.clientiPerFatturato();
            return new ResponseEntity<>(clientiPerFatturato, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerDataInserimento")
    public ResponseEntity<String> clientiPerDataInserimento() {
        try {
            String clientiPerDataInserimento = clienteService.clientiPerDataInserimento();
            return new ResponseEntity<>(clientiPerDataInserimento, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerDataUltimoContatto")
    public ResponseEntity<String> clientiPerDataUltimoContatto() {
        try {
            String clientiPerDataUltimoContatto = clienteService.clientiPerDataUltimoContatto();
            return new ResponseEntity<>(clientiPerDataUltimoContatto, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerProvinciaSedeLegale")
    public ResponseEntity<String> clientiPerProvinciaSedeLegale() {
        try {
            String clientiPerProvinciaSedeLegale = clienteService.clientiPerProvinciaSedeLegale();
            return new ResponseEntity<>(clientiPerProvinciaSedeLegale, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }




    //----------------------------------------------------------------------------------------------------

    @GetMapping("/fatturatoAnnuale")
    public ResponseEntity<?> getClientiByFatturatoAnnuale(@RequestParam double fatturatoAnnuale) {
        try {
            List<Cliente> listaClienti = clienteService.getByFatturatoAnnuale(fatturatoAnnuale);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dataInserimento")
    public ResponseEntity<?> getClienteByDataInserimento(@RequestParam LocalDate dataInserimento) {
        try {
            List<Cliente> listaClienti = clienteService.getByDataInserimento(dataInserimento);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Nessun cliente trovato!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dataUltimoContatto")
    public ResponseEntity<?> getClientiByUltimoContatto(@RequestParam LocalDate dataUltimoContatto) {
        try {
            List<Cliente> listaClienti = clienteService.getByDataUltimoContatto(dataUltimoContatto);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Nessun cliente trovato!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nomeContatto")
    public ResponseEntity<?> getClientiByNomeContatto(@RequestParam String nomeContatto) {
        try {
            List<Cliente> listaClienti = clienteService.getByNomeContatto(nomeContatto);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Nessun cliente trovato!", HttpStatus.BAD_REQUEST);
        }
    }
}
