package com.zeven.ets_proyect.Ets_Proyect.services;

import com.zeven.ets_proyect.Ets_Proyect.dto.SneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.SneakersResponseDTO;

import java.util.List;

public interface SupplierCatalogServices {

    SneakersResponseDTO getSneakerList();
    SneakerDTO getSneakerByCollectionId(Long collectionId);
}
