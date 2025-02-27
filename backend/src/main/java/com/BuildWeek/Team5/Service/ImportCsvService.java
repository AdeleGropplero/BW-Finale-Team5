package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Model.Imp.ImportCap;
import com.BuildWeek.Team5.Model.Imp.ImportComuniProvince;
import com.BuildWeek.Team5.Repository.Imp.ImportCapRepository;
import com.BuildWeek.Team5.Repository.Imp.ImportComuniProvinceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;

@Service
@RequiredArgsConstructor //capire bene come funziona ⭕️
public class ImportCsvService {
    private final ImportCapRepository capRepository;
    private final ImportComuniProvinceRepository comuniProvinceRepository;

    @Transactional
    public void  importComuniProvince(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line; //Capire meglio
            while ((line = br.readLine()) != null){ //va avanti per tutte le linee
                String[] data = line.split(";");
                if (data.length >= 5){
                    ImportComuniProvince comuniProvince = new ImportComuniProvince();
                    comuniProvince.setComune(data[0].trim());
                    comuniProvince.setRegione(data[1].trim());
                    comuniProvince.setProvincia(data[2].trim());
                    comuniProvince.setSigla(data[3].trim());
                    comuniProvince.setCodiceIstat(data[4].trim());
                    comuniProvinceRepository.save(comuniProvince);
                }
            }
            System.out.println("Tabella comuni e province importata! 🔔");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void importCap(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line; //Capire meglio
            while ((line = br.readLine()) != null) { //va avanti per tutte le linee
                String[] data = line.split(";");
                if (data.length >= 2) {
                    ImportCap cap = new ImportCap();
                    cap.setCodiceIstat(data[0].trim());
                    cap.setCap(data[1].trim());
                    capRepository.save(cap);
                }
            }
            System.out.println("Tabella Cap e province importata! 🔔");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
