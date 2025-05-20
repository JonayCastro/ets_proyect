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
        /**
         * This method is used to get the supplier data from the supplier catalog services.
         * The supplier data is used to get the products from the supplier catalog services.
         *
         * @param supplierId the supplier id
         *
         * @return the supplier data
         */
        return this.supplierCatalogServices.getSupplierData();
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getProducts() {
        /**
         * This method is used to get the products from the supplier catalog services.
         * The products are used to get the products from the supplier catalog services.
         *
         * @param supplierId the supplier id
         *
         * @return the products
         */
        try {
            List<SneakerDTO> products = this.supplierCatalogServices.getProducts();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiMessage.PRODUCT_LIST_CANT_GET);
        }
    }

    @Override
    @GetMapping(path = ApiPaths.STORED_PRODUCTS)
    public ResponseEntity<?> getStoredProducts() {
        try{
            List<SneakerDTO> storedProducts = this.supplierCatalogServices.getStoredProducts();
            return ResponseEntity.status(HttpStatus.OK).body(storedProducts);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiMessage.PRODUCT_LIST_CANT_GET);
        }
    }

    @Override
    @GetMapping(path = ApiPathVariables.PRODUCT_ID_PATH_VARIABLE)
    public SneakerDTO getProductById(@PathVariable Long productId) {
        /**
         * This method is used to get the product by id from the supplier catalog services.
         * The product is used to get the product from the supplier catalog services.
         *
         * @param productId the product id
         *
         * @return the product
         */
        return null;
    }

    @Override
    @GetMapping(path = ApiPathVariables.PRODUCT_NAME_PATH_VARIABLE)
    public SneakerDTO getProductByName(@PathVariable String productName) {
        /**
         * This method is used to get the product by name from the supplier catalog services.
         * The product is used to get the product from the supplier catalog services.
         *
         * @param productName the product name
         *
         * @return the product
         */
        return null;
    }

    @Override
    @GetMapping(path = ApiPathVariables.COLLECTION_ID_PATH_VARIABLE)
    public SneakerDTO getProductByBrand(@PathVariable String productBrand) {
        /**
         * This method is used to get the product by brand from the supplier catalog services.
         * The product is used to get the product from the supplier catalog services.
         *
         * @param productBrand the product brand
         *
         * @return the product
         */
        return null;
    }
}
