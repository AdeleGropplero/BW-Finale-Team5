package com.BuildWeek.Team5.Mapper_Travasi;

import com.BuildWeek.Team5.Enum.TipoSede;
import com.BuildWeek.Team5.Model.Imp.ImportCap;
import com.BuildWeek.Team5.Model.Imp.ImportComuniProvince;
import com.BuildWeek.Team5.Model.Indirizzo;
import com.BuildWeek.Team5.Payload.IndirizzoDTO;
import com.BuildWeek.Team5.Repository.Imp.ImportCapRepository;
import com.BuildWeek.Team5.Repository.Imp.ImportComuniProvinceRepository;
import org.springframework.stereotype.Component;

@Component
public class IndirizzoMapper {

    //Usiamo questo perchè l'autowired può creare problemi in un component.
    private final ImportCapRepository capRepository;
    private final ImportComuniProvinceRepository comuniProvinceRepository;

    public IndirizzoMapper(ImportCapRepository capRepository, ImportComuniProvinceRepository comuniProvinceRepository) {
        this.capRepository = capRepository;
        this.comuniProvinceRepository = comuniProvinceRepository;
    }

    public IndirizzoDTO entity_dto(Indirizzo entity) {
        IndirizzoDTO dto = new IndirizzoDTO();
        dto.setVia(entity.getVia());
        dto.setCivico(entity.getCivico());
        dto.setComune(entity.getComune());
        dto.setProvincia(entity.getProvincia());
        dto.setCap(entity.getCap());
        dto.setSede(entity.getSede());
        return dto;
    }

    public Indirizzo dto_entity(IndirizzoDTO dto) {
        Indirizzo entity = new Indirizzo();
        entity.setVia(dto.getVia());
        entity.setCivico(dto.getCivico());
        entity.setComune(dto.getComune());
        entity.setSede(dto.getSede());
        entity.setCap(recuperoCap(dto.getComune()));
        entity.setProvincia(recuperoProvincia(dto.getComune()));
        return entity;
    }

    //Metodi recupero cap e località
    public String recuperoProvincia(String comune){
        ImportComuniProvince provincia = comuniProvinceRepository.findFirstByComune(comune)
                .orElseThrow(()-> new RuntimeException("Provincia non trovata"));
        String sigla = provincia.getSigla();
        String regione = provincia.getRegione();
        return sigla + ", " + regione;
    }

    public String recuperoCap(String comune){
        ImportComuniProvince provincia = comuniProvinceRepository.findFirstByComune(comune)
                .orElseThrow(()-> new RuntimeException("Provincia non trovata"));

        String istat = provincia.getCodiceIstat();

        ImportCap capIstat = capRepository.findFirstByCodiceIstat(istat)
                .orElseThrow(()-> new RuntimeException("Cap non trovato"));

        String cap = capIstat.getCap();
        return cap;
    }

}
