package com.zeven.ets_proyect.Ets_Proyect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SneakerDTO {

    @JsonProperty("id_product")
    private Long originId;

    @JsonProperty("id_collection")
    private Long originCollectionId;

    @JsonProperty("available_sizes")
    private List<AvailableSizesDTO> size;

    @JsonProperty("image_alt")
    private String imageAlt;

    @JsonProperty("tax_rate")
    private String taxRate;

    @JsonProperty("original_price")
    private Integer originalPrice;

    @JsonProperty("price_base")
    private Double priceBase;

    @JsonProperty("tax_amount")
    private Double taxAmount;

    private Integer price;
    private String brand;
    private String image;
    private String link;
    private String name;
    private String reference;
}
