package com.zeven.ets_proyect.Ets_Proyect.dto.sneakers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteSneakerDTO extends SneakerDTO {

    private Long idFavorite;
}
