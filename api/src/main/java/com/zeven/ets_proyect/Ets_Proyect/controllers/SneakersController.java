package com.zeven.ets_proyect.Ets_Proyect.controllers;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPathVariables;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPaths;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakersDataDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.SupplierCatalogServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = ApiPaths.SNEAKERS_PATH)
public class SneakersController implements ProductController<SneakersDataDTO, SneakerDTO>{

    private final SupplierCatalogServices<SneakersDataDTO, SneakerDTO, Object> supplierCatalogServices;

    SneakersController (SupplierCatalogServices<SneakersDataDTO, SneakerDTO, Object> supplierCatalogServices) {
        this.supplierCatalogServices = supplierCatalogServices;
    }

    @Override
    @GetMapping(path = ApiPaths.SUPPLIER_DATA_PATH)
    public SneakersDataDTO getSupplierData() {
        return this.supplierCatalogServices.getSupplierData();
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getProducts() {
        try {
            List<SneakerDTO> products = this.supplierCatalogServices.getProducts();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiMessage.PRODUCT_LIST_CANT_GET);
        }
    }

    @Override
    @GetMapping(path = ApiPathVariables.PRODUCT_ID_PATH_VARIABLE)
    public SneakerDTO getProductById(@PathVariable Long productId) {
        return null;
    }

    @Override
    @GetMapping(path = ApiPathVariables.PRODUCT_NAME_PATH_VARIABLE)
    public SneakerDTO getProductByName(@PathVariable String productName) {
        return null;
    }

    @Override
    @GetMapping(path = ApiPathVariables.COLLECTION_ID_PATH_VARIABLE)
    public SneakerDTO getProductByBrand(@PathVariable String productBrand) {
        return null;
    }
}
