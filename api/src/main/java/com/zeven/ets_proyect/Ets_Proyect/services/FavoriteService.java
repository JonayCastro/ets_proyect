package com.zeven.ets_proyect.Ets_Proyect.services;


import com.zeven.ets_proyect.Ets_Proyect.dto.FilterDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteChangedDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;

import java.util.List;

public interface FavoriteService {

    FavoriteSneaker getFavoriteById(Long favoriteId);
    void addFavoriteBySneakerId(Long sneakerStoredId);
    void deleteFavoriteById(Long favoriteSneakerId);
    List<FavoriteChangedDTO> getFavoriteChanged();
    List<FavoriteChangedDTO> getFavoriteChangedByName(FilterDTO filterDTO);
    List<FavoriteChangedDTO> getFavoriteChangedByPriceRange(FilterDTO filterDTO);
    List<FavoriteSneakerDTO> getFavoritesList();
    List<FavoriteSneakerDTO> getFavoritesListByBrand(FilterDTO filterDto);
    List<FavoriteSneakerDTO> getFavoritesListByPrice(FilterDTO filterDto);
}
