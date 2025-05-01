package com.zeven.ets_proyect.Ets_Proyect.dto.sneakers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SneakerProductsDTO {
    List<SneakerDTO> products;
}
