package com.zeven.ets_proyect.Ets_Proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCtrlDTO {

    private Long userId;
    private Long favoriteId;
}
