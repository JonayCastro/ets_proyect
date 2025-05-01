package com.zeven.ets_proyect.Ets_Proyect.controllers;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPathVariables;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPaths;
import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ApiPaths.USERS_PATH)
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = ApiPaths.ADD_FAVORITE_PATH)
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteSneakerDTO favoriteSneakerDTO){
        try {
            this.userService.addFavoriteToUser(favoriteSneakerDTO);
            return ResponseEntity.status(HttpStatus.OK).body(ApiMessage.FAVORITE_ADDED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiMessage.FAVORITE_CANT_BE_ADDED);
        }
    }

    @DeleteMapping(path = ApiPaths.DELETE_FAVORITE_PATH + ApiPathVariables.FAVORITE_ID_PATH_VARIABLE)
    public ResponseEntity<?> deleteFavorite(@PathVariable Long favoriteSneakerId ){
        try {
            this.userService.deleteFavoriteById(favoriteSneakerId);
            return ResponseEntity.status(HttpStatus.OK).body(ApiMessage.FAVORITE_REMOVED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiMessage.FAVORITE_CANT_BE_REMOVED);
        }
    }

}
