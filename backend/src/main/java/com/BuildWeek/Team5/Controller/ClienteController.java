package com.BuildWeek.Team5.Controller;

import com.BuildWeek.Team5.Exception.*;
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


    //AGGIUNGI un nuovo CLIENTE
    //POSTMAN --> http://localhost:8080/cliente/nuovo
    @PostMapping("/nuovo")
    public ResponseEntity<String> nuovoCliente(@RequestBody @Validated ClienteDTO clienteDTO, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for (ObjectError error : validation.getAllErrors()) {
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            //invio immagine al servizio Cloudinary
            //Map mappaUpload = cloudinary.uploader().upload(logoAziendale.getBytes(), ObjectUtils.emptyMap());
            // l'indirizzo dell'immagine
            //String urlImage = mappaUpload.get("secure_url").toString();
            // set che setta la nuova immagine

            //ℹ️ℹ️ℹ️ tutti i clienti hanno lo stesso logo
            //for (ClienteDTO singoloCliente : clienteDTOArrayList) {
             //   singoloCliente.setLogoAziendale(urlImage);
           // }

            Cliente cliente = clienteService.fromClienteDTOtoCliente(clienteDTO);
            long idCliente = clienteService.salvaCliente(cliente);
            return new ResponseEntity<>("Il cliente con ID " + idCliente + " è stato salvato correttamente!", HttpStatus.CREATED);
        } catch (EmailDuplicated | PartitaIvaDuplicated e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //AGGIUNGI una FATTURA al CLIENTE.
    //POSTMAN --> http://localhost:8080/cliente/addFattura/{clienteID}
    @PatchMapping("/addFattura/{clienteID}")
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
            fatturaDTO.setCliente(cliente);
            Fattura fattura = fatturaService.fromFatturaDTOtoFattura(fatturaDTO);
            long idFattura = fatturaService.salvaFattura(fattura);
            return new ResponseEntity<>("La fattura con ID " + idFattura + " è stata aggiunta con successo!", HttpStatus.CREATED);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //AGGIUNGI un INDIRIZZO al CLIENTE.
    //POSTMAN --> http://localhost:8080/cliente/addIndirizzo/{clienteID}
    @PatchMapping("/addIndirizzo/{clienteID}")
    public ResponseEntity<String> addIndirizzoAlCliente(@RequestBody @Validated IndirizzoDTO indirizzoDTO, BindingResult validation, @PathVariable Long clienteID) {
        if (validation.hasErrors()) {
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for (ObjectError error : validation.getAllErrors()) {
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            String indirizzoAggiunto = indirizzoService.addIndirizzo(clienteID, indirizzoDTO);
            return new ResponseEntity<>(indirizzoAggiunto, HttpStatus.CREATED);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //post Indirizzo. (questo endpoint ci serviva in fase iniziale ma dubito che lo useremo più, dopo l'aggiunta della patch)
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

    //post Indirizzi. (questo endpoint ci serviva in fase iniziale ma dubito che lo useremo più, dopo l'aggiunta della patch)
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
    public ResponseEntity<?> getClientiAZ() {
        try {
            List<ClienteDTO> clientiAZ = clienteService.clientiAZ();
            return new ResponseEntity<>(clientiAZ, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerFatturato")
    public ResponseEntity<?> clientiPerFatturato() {
        try {
            List<ClienteDTO> clientiPerFatturato = clienteService.clientiPerFatturato();
            return new ResponseEntity<>(clientiPerFatturato, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerDataInserimento")
    public ResponseEntity<?> clientiPerDataInserimento() {
        try {
            List<ClienteDTO> clientiPerDataInserimento = clienteService.clientiPerDataInserimento();
            return new ResponseEntity<>(clientiPerDataInserimento, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerDataUltimoContatto")
    public ResponseEntity<?> clientiPerDataUltimoContatto() {
        try {
            List<ClienteDTO> clientiPerDataUltimoContatto = clienteService.clientiPerDataUltimoContatto();
            return new ResponseEntity<>(clientiPerDataUltimoContatto, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clientiPerProvinciaSedeLegale")
    public ResponseEntity<?> clientiPerProvinciaSedeLegale() {
        try {
            List<ClienteDTO> clientiPerProvinciaSedeLegale = clienteService.clientiPerProvinciaSedeLegale();
            return new ResponseEntity<>(clientiPerProvinciaSedeLegale, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    //----------------------------------------------------------------------------------------------------
    //Filtra liste clienti --------------------------------------------------
    @GetMapping("/fatturatoAnnuale")
    public ResponseEntity<?> getClientiByFatturatoAnnuale(@RequestParam double fatturatoAnnuale) {
        try {
            List<ClienteDTO> listaClienti = clienteService.getByFatturatoAnnuale(fatturatoAnnuale);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Non è stato trovato nessun cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dataInserimento")
    public ResponseEntity<?> getClienteByDataInserimento(@RequestParam LocalDate dataInserimento) {
        try {
            List<ClienteDTO> listaClienti = clienteService.getByDataInserimento(dataInserimento);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Nessun cliente trovato!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dataUltimoContatto")
    public ResponseEntity<?> getClientiByUltimoContatto(@RequestParam LocalDate dataUltimoContatto) {
        try {
            List<ClienteDTO> listaClienti = clienteService.getByDataUltimoContatto(dataUltimoContatto);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Nessun cliente trovato!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nomeContatto")
    public ResponseEntity<?> getClientiByNomeContatto(@RequestParam String nomeContatto) {
        try {
            List<ClienteDTO> listaClienti = clienteService.getByNomeContatto(nomeContatto);
            return new ResponseEntity<>(listaClienti, HttpStatus.OK);
        } catch (ClienteNotFound e) {
            return new ResponseEntity<>("Nessun cliente trovato!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allClients")
    public List<Cliente> getAllClients(){
        return clienteRepository.findAll();
    }
    //----------------------------------------------------------------------------------------------------
}
