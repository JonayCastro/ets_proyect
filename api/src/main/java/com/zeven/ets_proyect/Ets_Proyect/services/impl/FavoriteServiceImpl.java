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
        /**
         * This method retrieves a favorite sneaker by its ID from the repository.
         * If the favorite sneaker is not found, it throws a RuntimeException with a message indicating that the favorite was not found.
         * The method returns the found FavoriteSneaker object.
         *
         * @param favoriteId The ID of the favorite sneaker to retrieve.
         *
         * @return The FavoriteSneaker object corresponding to the provided ID.
         */
        return this.favoriteSneakersRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException(ApiMessage.FAVORITE_NOT_FOUND));
    }

    @Override
    public List<FavoriteChangedDTO> getFavoriteChanged() {
        /**
         * This method retrieves a list of favorite sneakers that have been changed from the repository.
         * It returns a list of FavoriteChangedDTO objects, which contain information about the changed favorites.
         * @return A list of FavoriteChangedDTO objects representing the changed favorites.
         */
        return this.favoriteSneakersRepository.findTopFavoriteChanged();
    }
}
