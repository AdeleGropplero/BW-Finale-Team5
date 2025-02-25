package com.BuildWeek.Team5.Controller;

import com.BuildWeek.Team5.Payload.ClienteDTO;
import com.BuildWeek.Team5.Service.ClienteService;
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
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    Cloudinary cloudinary;

    @PostMapping("/nuovo")
    public ResponseEntity<String> nuovoCliente(@RequestPart("dto") @Validated ClienteDTO clienteDTO, BindingResult validation, @RequestPart("logoAziendale") MultipartFile logoAziendale) throws IOException {
        if(validation.hasErrors()){
            StringBuilder messaggio = new StringBuilder("Problemi nella validazione dei dati: \n");

            for(ObjectError error : validation.getAllErrors()){
                messaggio.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(messaggio.toString(), HttpStatus.BAD_REQUEST);
        }

       try{

        //invio immagine al servizio Cloudinary
        Map mappaUpload = cloudinary.uploader().upload(logoAziendale.getBytes(), ObjectUtils.emptyMap());
        // l'indirizzo dell'immagine
        String urlImage = mappaUpload.get("secure_url").toString();
        // set che setta la nuova immagine
        clienteDTO.setLogoAziendale(urlImage);


        long id = clienteService.salvaCliente(clienteDTO);
        return new ResponseEntity<>("Il cliente con id: " + id + " è stato salvato correttamente!", HttpStatus.CREATED);

       }catch (IOException e){
           return new ResponseEntity<>("Errore!" , HttpStatus.BAD_REQUEST);
       }
    }
}
