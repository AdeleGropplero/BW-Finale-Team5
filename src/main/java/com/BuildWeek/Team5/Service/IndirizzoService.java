package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Model.Indirizzo;
import com.BuildWeek.Team5.Payload.IndirizzoDTO;
import com.BuildWeek.Team5.Repository.Imp.ImportCapRepository;
import com.BuildWeek.Team5.Repository.Imp.ImportComuniProvinceRepository;
import com.BuildWeek.Team5.Repository.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {
    @Autowired
    IndirizzoRepository indirizzoRepository;

    @Autowired
    ImportCapRepository capRepository;

    @Autowired
    ImportComuniProvinceRepository comuniProvinceRepository;



}
