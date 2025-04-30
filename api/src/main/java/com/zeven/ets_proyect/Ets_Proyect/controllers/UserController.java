package com.zeven.ets_proyect.Ets_Proyect.controllers;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPaths;
import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteCtrlDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ApiPaths.USERS_PATH)
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = ApiPaths.ADD_FAVORITE_PATH)
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteCtrlDTO favoriteCtrlDTO){
        try {
            this.userService.addFavoriteToUser(favoriteCtrlDTO);
            return ResponseEntity.status(HttpStatus.OK).body(ApiMessage.FAVORITE_ADDED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiMessage.FAVORITE_CANT_BE_ADDED);
        }
    }

    @PostMapping(path = ApiPaths.REMOVE_FAVORITE_PATH)
    public ResponseEntity<?> removeFavorite(@RequestBody FavoriteCtrlDTO favoriteCtrlDTO){
        try {
            this.userService.addFavoriteToUser(favoriteCtrlDTO);
            return ResponseEntity.status(HttpStatus.OK).body(ApiMessage.FAVORITE_REMOVED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiMessage.FAVORITE_CANT_BE_REMOVED);
        }
    }

}
