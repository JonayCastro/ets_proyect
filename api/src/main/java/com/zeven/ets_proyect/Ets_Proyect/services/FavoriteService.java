package com.zeven.ets_proyect.Ets_Proyect.services;


import com.zeven.ets_proyect.Ets_Proyect.dto.FilterDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.OffersDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;

import java.util.List;

public interface FavoriteService {

    FavoriteSneaker getFavoriteById(Long favoriteId);
    void addFavoriteBySneakerId(Long sneakerStoredId);
    void deleteFavoriteById(Long favoriteSneakerId);
    List<OffersDTO> getFavoriteChanged();
    List<OffersDTO> getFavoriteChangedByName(FilterDTO filterDTO);
    List<OffersDTO> getFavoriteChangedByPriceRange(FilterDTO filterDTO);
    List<FavoriteSneakerDTO> getFavoritesList();
    List<FavoriteSneakerDTO> getFavoritesListByBrand(FilterDTO filterDto);
    List<FavoriteSneakerDTO> getFavoritesListByPrice(FilterDTO filterDto);
}
