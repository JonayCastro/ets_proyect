package com.zeven.ets_proyect.Ets_Proyect.dto.sneakers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteChangedDTO {

    private Long userId;
    private Integer oldPrice;
    private String sneakerName;
    private String brand;
    private String link;
    private Integer newPrice;
    private String userName;
    private Long chatId;
}
