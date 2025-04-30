package com.zeven.ets_proyect.Ets_Proyect.services;

import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteCtrlDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.SneakersResponseDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    void createUser(UserDTO userDTO);
    String encryptPassword(String originalPassword);
    SneakersResponseDTO login(UserDTO userDTO);
    void addFavoriteToUser(FavoriteCtrlDTO favoriteCtrlDTO);
    void removeFavoriteFromUser(FavoriteCtrlDTO favoriteCtrlDTO);
}