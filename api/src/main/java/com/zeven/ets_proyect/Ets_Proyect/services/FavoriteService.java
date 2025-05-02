package com.zeven.ets_proyect.Ets_Proyect.services;


import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteChangedDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;

import java.util.List;

public interface FavoriteService {

    FavoriteSneaker getFavoriteById(long favoriteId);
    List<FavoriteChangedDTO> getFavoriteChanged();
}
