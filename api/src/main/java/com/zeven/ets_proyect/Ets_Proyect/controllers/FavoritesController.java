package com.zeven.ets_proyect.Ets_Proyect.controllers;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPathVariables;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPaths;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = ApiPaths.FAVORITES_PATH)
public class FavoritesController {

    private final FavoriteService favoriteService;

    FavoritesController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @GetMapping(path = ApiPaths.LIST_PATH)
    public ResponseEntity<?> listFavorites(){
        try {
            List<FavoriteSneakerDTO> favoriteSneakerDTO = this.favoriteService.getFavoritesList();
            return ResponseEntity.status(HttpStatus.OK).body(favoriteSneakerDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiMessage.FAVORITES_LIST_CANT_BE_GET);
        }
    }

    @PutMapping(path = ApiPaths.ADD_PATH + ApiPathVariables.SNEAKER_ID_PATH_VARIABLE)
    public ResponseEntity<?> addFavorite(@PathVariable Long sneakerId){
        try {
            this.favoriteService.addFavoriteBySneakerId(sneakerId);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", ApiMessage.FAVORITE_ADDED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", ApiMessage.FAVORITE_CANT_BE_ADDED));
        }
    }

    @DeleteMapping(path = ApiPaths.DELETE_PATH + ApiPathVariables.FAVORITE_ID_PATH_VARIABLE)
    public ResponseEntity<?> deleteFavorite(@PathVariable Long favoriteSneakerId ){
        try {
            this.favoriteService.deleteFavoriteById(favoriteSneakerId);
            return ResponseEntity.status(HttpStatus.OK).body(ApiMessage.FAVORITE_REMOVED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiMessage.FAVORITE_CANT_BE_REMOVED);
        }
    }

}
