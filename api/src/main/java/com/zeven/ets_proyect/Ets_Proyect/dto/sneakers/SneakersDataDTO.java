package com.zeven.ets_proyect.Ets_Proyect.dto.sneakers;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SneakersDataDTO {

    @JsonProperty("total_products")
    private Integer totalProducts;

    @JsonProperty("total_pages")
    private Integer totalPages;

    private Integer page;


    //private List<SneakerDTO> products;
}
