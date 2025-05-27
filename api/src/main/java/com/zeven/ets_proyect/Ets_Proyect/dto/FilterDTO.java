package com.zeven.ets_proyect.Ets_Proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {

    String key;
    String brandFilter;
    Integer minPriceFilter;
    Integer maxPriceFilter;
}
