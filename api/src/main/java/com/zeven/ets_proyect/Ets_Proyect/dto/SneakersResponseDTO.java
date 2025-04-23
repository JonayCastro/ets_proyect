package com.zeven.ets_proyect.Ets_Proyect.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SneakersResponseDTO {

    @JsonProperty("total_products")
    private Integer totalProducts;

    @JsonProperty("total_pages")
    private Integer totalPages;

    private List<SneakerDTO> products;
    private Integer page;
}
