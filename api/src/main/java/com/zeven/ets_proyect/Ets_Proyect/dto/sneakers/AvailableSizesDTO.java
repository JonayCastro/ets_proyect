package com.zeven.ets_proyect.Ets_Proyect.dto.sneakers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSizesDTO {

    private String scale;
    private Integer type;
    private String sizes;
}
