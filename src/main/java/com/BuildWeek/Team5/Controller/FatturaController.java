package com.BuildWeek.Team5.Controller;

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

    @GetMapping("/{idCliente}")
    public ResponseEntity<?> getFatturaByIdCliente(@PathVariable Long idCliente){
        try{
            List<Fattura> listaFatture = fatturaService.getByIdCliente(idCliente);
            return new ResponseEntity<>(listaFatture, HttpStatus.OK);
        }catch (FatturaNotFound e){
            return new ResponseEntity<>("Nessuna fattura trovata!", HttpStatus.BAD_REQUEST);
        }
    }
}
