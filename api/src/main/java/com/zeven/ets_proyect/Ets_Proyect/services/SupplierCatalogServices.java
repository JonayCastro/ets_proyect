package com.zeven.ets_proyect.Ets_Proyect.services;

import java.util.List;

public interface SupplierCatalogServices <DataSupplier, Product, FilterCriteria> {

    DataSupplier getSupplierData();
    void persistDataSupplier(DataSupplier dataSupplier);
    List<Product> getProducts();
    List<Product> getStoredProducts();
    void persistProducts (List<Product> productList);
    Product getProduct(FilterCriteria productIdentifier);
    String buildUrl(String page);
}
