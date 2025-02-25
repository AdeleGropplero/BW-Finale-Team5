package com.BuildWeek.Team5.Runner;

import com.BuildWeek.Team5.Service.ImportCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    ImportCsvService importCsvService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("⚡ Importazione CSV avviata...");
        importCsvService.importComuniProvince("src/main/resources/comuni-provincie.csv");
        importCsvService.importCap("src/main/resources/cap.csv");
        System.out.println("✅ Importazione completata!");
    }
}
