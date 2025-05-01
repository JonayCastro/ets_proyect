package com.zeven.ets_proyect.Ets_Proyect.enums;

public enum SupplierNamesEnum {

    SNEAKER_SUPPLIER_IDENTIFIER("sneaker");

    private final String value;

    SupplierNamesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
