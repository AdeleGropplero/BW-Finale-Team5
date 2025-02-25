package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Model.Fattura;
import com.BuildWeek.Team5.Payload.FatturaDTO;
import com.BuildWeek.Team5.Repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FatturaService {

    @Autowired
    FatturaRepository fatturaRepository;

    public Long salvaFattura(Fattura fattura){
        fatturaRepository.save(fattura);
        return fattura.getId();
    }

    //travaso
    public Fattura fromFatturaDTOtoFattura(FatturaDTO fatturaDTO){
        Fattura fattura = new Fattura();
        fattura.setDataFattura(fatturaDTO.getDataFattura());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setStatoFattura(fatturaDTO.getStatoFattura());
        return fattura;
    }

    public FatturaDTO fromFatturaToFatturaDTO(Fattura fattura){
        FatturaDTO fatturaDTO = new FatturaDTO();
        fatturaDTO.setDataFattura(fattura.getDataFattura());
        fatturaDTO.setImporto(fattura.getImporto());
        fatturaDTO.setStatoFattura(fattura.getStatoFattura());
        return fatturaDTO;
    }

}
