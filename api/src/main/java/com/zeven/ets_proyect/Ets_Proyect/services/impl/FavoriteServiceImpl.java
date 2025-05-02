package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteChangedDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import com.zeven.ets_proyect.Ets_Proyect.repositories.FavoriteSneakersRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteSneakersRepository favoriteSneakersRepository;

    FavoriteServiceImpl(FavoriteSneakersRepository favoriteSneakersRepository){
        this.favoriteSneakersRepository = favoriteSneakersRepository;
    }


    @Override
    public FavoriteSneaker getFavoriteById(long favoriteId) {
        return this.favoriteSneakersRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException(ApiMessage.FAVORITE_NOT_FOUND));
    }

    @Override
    public List<FavoriteChangedDTO> getFavoriteChanged() {
        return this.favoriteSneakersRepository.findTopFavoriteChanged();
    }
}
