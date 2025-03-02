package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Enum.StatoFattura;
import com.BuildWeek.Team5.Exception.ClienteNotFound;
import com.BuildWeek.Team5.Exception.FatturaNotFound;
import com.BuildWeek.Team5.Exception.StatoFatturaNotFoundException;
import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Model.Fattura;
import com.BuildWeek.Team5.Payload.FatturaDTO;
import com.BuildWeek.Team5.Repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FatturaService {

    @Autowired
    FatturaRepository fatturaRepository;

    public Long salvaFattura(Fattura fattura){
        fatturaRepository.save(fattura);
        return fattura.getId();
    }

    // ricerca fattura da Id cliente
    public List<Fattura> getByIdCliente(Long idCliente){
        List<Fattura> fattureByCliente = fatturaRepository.findByIdCliente(idCliente);
        if(fattureByCliente.isEmpty()){
            throw new FatturaNotFound("Nessuna fattura trovata!");
        }
        return fattureByCliente;
    }

    // ricerca fattura da stato fattura
    public List<Fattura> getByStatoFattura(String stringStatoFattura){
        try {
            StatoFattura statoFattura = StatoFattura.valueOf(stringStatoFattura.toUpperCase());
            List<Fattura> fattureByStatoFattura = fatturaRepository.findByStatoFattura(statoFattura);
            if (fattureByStatoFattura.isEmpty()) {
                throw new FatturaNotFound("Nessuna fattura trovata!");
            }
            return fattureByStatoFattura;
        } catch (IllegalArgumentException e) {
            throw new StatoFatturaNotFoundException(("Stato fattura non valido: ") + stringStatoFattura);
        }
    }

    // ricerca fattura da data fattura
    public List<Fattura> getByDataFattura(LocalDate dataFattura){
        List<Fattura> fattureByDataFattura = fatturaRepository.findByDataFatturaBefore(dataFattura);
        if(fattureByDataFattura.isEmpty()){
            throw new FatturaNotFound("Nessuna fattura trovata!");
        }
        return fattureByDataFattura;
    }

    // ricerca fattura per anno della data di fattura
    public List<Fattura> getByAnnoFattura(int annoFattura){
        List<Fattura> fattureByAnnoFattura = fatturaRepository.findByDataFattura(annoFattura);
        if(fattureByAnnoFattura.isEmpty()){
            throw new FatturaNotFound("Nessuna fattura trovata!");
        }
        return fattureByAnnoFattura;
    }

    // ricerca fatture per range di importi
    public List<Fattura> getByImporto(double importoMin, double importoMax){
        List<Fattura> fattureByImporto = fatturaRepository.findByImportoBetween(importoMin, importoMax);
        if(fattureByImporto.isEmpty()){
            throw new FatturaNotFound("Nessuna fattura trovata!");
        }
        return fattureByImporto;
    }

    //travaso
    public Fattura fromFatturaDTOtoFattura(FatturaDTO fatturaDTO){
        Fattura fattura = new Fattura();
        fattura.setDataFattura(fatturaDTO.getDataFattura());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setStatoFattura(fatturaDTO.getStatoFattura());
        fattura.setCliente(fatturaDTO.getCliente());
        return fattura;
    }

    public FatturaDTO fromFatturaToFatturaDTO(Fattura fattura){
        FatturaDTO fatturaDTO = new FatturaDTO();
        fatturaDTO.setDataFattura(fattura.getDataFattura());
        fatturaDTO.setImporto(fattura.getImporto());
        fatturaDTO.setStatoFattura(fattura.getStatoFattura());
        fatturaDTO.setCliente(fattura.getCliente());
        return fatturaDTO;
    }


}
