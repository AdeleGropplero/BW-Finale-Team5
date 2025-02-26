package com.BuildWeek.Team5.Controller;

import com.BuildWeek.Team5.Enum.StatoFattura;
import com.BuildWeek.Team5.Exception.ClienteNotFound;
import com.BuildWeek.Team5.Exception.FatturaNotFound;
import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Model.Fattura;
import com.BuildWeek.Team5.Service.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fattura")
public class FatturaController {
    @Autowired
    FatturaService fatturaService;

    // ricerca fatture da Id cliente
    @GetMapping("/{idCliente}")
    public ResponseEntity<?> getFatturaByIdCliente(@PathVariable Long idCliente){
        try{
            List<Fattura> listaFatture = fatturaService.getByIdCliente(idCliente);
            return new ResponseEntity<>(listaFatture, HttpStatus.OK);
        }catch (FatturaNotFound e){
            return new ResponseEntity<>("Nessuna fattura trovata!", HttpStatus.BAD_REQUEST);
        }
    }

    // ricerca fatture da stato fattura
    @GetMapping("/statoFattura")
    public ResponseEntity<?> getFattureByStatoFattura(@RequestParam String statoFattura){
        try {
            List<Fattura> listaFatture = fatturaService.getByStatoFattura(statoFattura);
            return new ResponseEntity<>(listaFatture, HttpStatus.OK);
        } catch (FatturaNotFound e){
            return new ResponseEntity<>("Nessuna fattura trovata!", HttpStatus.BAD_REQUEST);
        }
    }

    // ricerca fatture da data fattura
    @GetMapping("/dataFattura")
    public ResponseEntity<?> getFattureByDataFattura(@RequestParam LocalDate dataFattura){
        try{
            List<Fattura> listaFatture = fatturaService.getByDataFattura(dataFattura);
            return new ResponseEntity<>(listaFatture, HttpStatus.OK);
        }catch (FatturaNotFound e ){
            return new ResponseEntity<>("Nessuna fattura trovata!", HttpStatus.BAD_REQUEST);
        }
    }

    // ricerca fatture da data fattura
    @GetMapping("/annoFattura")
    public ResponseEntity<?> getFattureByAnnoFattura(@RequestParam int annoFattura){
        try{
            List<Fattura> listaFatture = fatturaService.getByAnnoFattura(annoFattura);
            return new ResponseEntity<>(listaFatture, HttpStatus.OK);
        }catch (FatturaNotFound e ){
            return new ResponseEntity<>("Nessuna fattura trovata!", HttpStatus.BAD_REQUEST);
        }
    }

    // ricerca fatture tramite importi
    @GetMapping("/importo")
    public ResponseEntity<?> getFattureByAnnoFattura(@RequestParam double importoMin, @RequestParam double importoMax){
        try{
            List<Fattura> listaFatture = fatturaService.getByImporto(importoMin, importoMax);
            return new ResponseEntity<>(listaFatture, HttpStatus.OK);
        }catch (FatturaNotFound e ){
            return new ResponseEntity<>("Nessuna fattura trovata!", HttpStatus.BAD_REQUEST);
        }
    }
}
