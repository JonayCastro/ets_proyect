package com.zeven.ets_proyect.Ets_Proyect.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteSneakerDTO {

    private Long idFavorite;
    private Long originId;
    private String name;
    private String brand;
    private String size;
    private String color;
    private Integer price;
}
