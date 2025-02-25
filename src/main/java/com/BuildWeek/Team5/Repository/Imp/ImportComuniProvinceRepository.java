package com.BuildWeek.Team5.Repository.Imp;

import com.BuildWeek.Team5.Model.Imp.ImportComuniProvince;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImportComuniProvinceRepository extends JpaRepository<ImportComuniProvince, Long> {

    Optional<ImportComuniProvince> findByComune(String comune);

    Optional<ImportComuniProvince> findByProvincia(String provincia);

    Optional<ImportComuniProvince> findByCodiceIstat(String codiceIstat);
}
