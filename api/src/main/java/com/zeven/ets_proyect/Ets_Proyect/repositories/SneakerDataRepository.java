package com.zeven.ets_proyect.Ets_Proyect.repositories;

import com.zeven.ets_proyect.Ets_Proyect.entities.SneakerDataEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SneakerDataRepository extends CrudRepository<SneakerDataEntity, Long> {

    Optional<SneakerDataEntity> findBySupplierIdentifier(String supplierIdentifier);
    void deleteBySupplierIdentifier(String supplierIdentifier);
}
