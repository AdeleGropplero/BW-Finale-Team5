package com.BuildWeek.Team5.Mapper_Travasi;

import com.BuildWeek.Team5.Model.Indirizzo;
import com.BuildWeek.Team5.Payload.IndirizzoDTO;
import com.BuildWeek.Team5.Repository.Imp.ImportCapRepository;
import com.BuildWeek.Team5.Repository.Imp.ImportComuniProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndirizzoMapper {
    @Autowired
    ImportCapRepository capRepository;

    @Autowired
    ImportComuniProvinceRepository comuniProvinceRepository;

    public IndirizzoDTO entity_dto(Indirizzo entity) {
        IndirizzoDTO dto = new IndirizzoDTO();
        dto.setVia(entity.getVia());
        dto.setCivico(entity.getCivico());
        dto.setComune(entity.getComune());
        dto.setLocalita(entity.getLocalita());
        dto.setCap(entity.getCap());
        return dto;
    }

/*
    public Indirizzo dto_entity(IndirizzoDTO dto) {
        Indirizzo entity = new Indirizzo();
        entity.setVia(dto.getVia());
        entity.setCivico(dto.getCivico());
        entity.setComune(dto.getComune());
        entity.setCap(*/
/*inserisci metodo*//*
);
        entity.setLocalita(*/
/*inserisci metodo*//*
);
        return entity;
    }
*/

    //Metodi recupero cap e località


}
