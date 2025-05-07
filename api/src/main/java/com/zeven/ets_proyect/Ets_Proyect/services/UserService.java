package com.zeven.ets_proyect.Ets_Proyect.services;

import com.zeven.ets_proyect.Ets_Proyect.dto.LoginDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;

public interface UserService {

    LoginDTO createUser(UserDTO userDTO);
    String encryptPassword(String originalPassword);
    String login(UserDTO userDTO);
    void addFavoriteToUser(FavoriteSneakerDTO favoriteSneakerDTO);
    void deleteFavoriteById(Long favoriteSneakerId);
    void updateChatId(String contact, Long chatId);
    String generateToken(Long userId);
}