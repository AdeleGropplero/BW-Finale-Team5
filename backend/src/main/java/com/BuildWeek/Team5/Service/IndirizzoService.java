package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Mapper_Travasi.IndirizzoMapper;
import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Model.Indirizzo;
import com.BuildWeek.Team5.Payload.IndirizzoDTO;
import com.BuildWeek.Team5.Repository.ClienteRepository;
import com.BuildWeek.Team5.Repository.Imp.ImportCapRepository;
import com.BuildWeek.Team5.Repository.Imp.ImportComuniProvinceRepository;
import com.BuildWeek.Team5.Repository.IndirizzoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IndirizzoService {
    @Autowired
    IndirizzoRepository indirizzoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    IndirizzoMapper mapper;

    public String insertIndirizzo(IndirizzoDTO dto) {
        Indirizzo indirizzo = mapper.dto_entity(dto);
        indirizzoRepository.save(indirizzo);
        IndirizzoDTO indirizzoDTO = mapper.entity_dto(indirizzo);
        return indirizzoDTO.toString();
    }

    public String insertIndirizzi(List<IndirizzoDTO> indirizziDTO) {
        List<Indirizzo> indirizzi = indirizziDTO.stream().map(indirizzoDto -> mapper.dto_entity(indirizzoDto))
                .collect(Collectors.toList());

        indirizzoRepository.saveAll(indirizzi);

        List<IndirizzoDTO> indirizziDto = indirizzi.stream().map(indirizzo -> mapper.entity_dto(indirizzo))
                .collect(Collectors.toList());

        return indirizziDto.toString();
    }

    public String addIndirizzo(Long id, IndirizzoDTO dto){
        Cliente cliente = clienteService.trovaCliente(id);
        insertIndirizzo(dto);
        Indirizzo indirizzo = mapper.dto_entity(dto);
        cliente.getIndirizzi().add(indirizzo);
        //non serve dare il save in teoria perchè c'è @Transactional
        return "Indirizzo: " + indirizzo + " aggiunto al cliente con id: " + id;
    }


}
