package com.zeven.ets_proyect.Ets_Proyect.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductController <SupplierData, Product> {

    SupplierData getSupplierData();
    ResponseEntity<?> getProducts();
    Product getProductById(Long productId);
    Product getProductByName(String productName);
    Product getProductByBrand(String productBrand);
}
