package com.zeven.ets_proyect.Ets_Proyect.services;

import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;

public interface UserService {

    void createUser(UserDTO userDTO);
    String encryptPassword(String originalPassword);
    String login(UserDTO userDTO);
    void addFavoriteToUser(FavoriteSneakerDTO favoriteSneakerDTO);
    void deleteFavoriteById(Long favoriteSneakerId);
}