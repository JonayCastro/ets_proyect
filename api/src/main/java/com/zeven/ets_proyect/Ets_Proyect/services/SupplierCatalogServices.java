package com.zeven.ets_proyect.Ets_Proyect.services;

import com.zeven.ets_proyect.Ets_Proyect.dto.FilterDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakerDTO;

import java.util.List;

public interface SupplierCatalogServices <DataSupplier, ProductDTO, EntityProduct> {

    EntityProduct findEntityById(Long productId);
    DataSupplier getSupplierData();
    void persistDataSupplier(DataSupplier dataSupplier);
    List<ProductDTO> getProducts();
    List<ProductDTO> getStoredProducts();
    void persistProducts (List<ProductDTO> productDTOList);
    String buildUrl(String page);
    List<SneakerDTO> getSneakerListByBrand(FilterDTO filterDto);
    List<SneakerDTO> getSneakerListByPrice(FilterDTO filterDto);
}
