package com.zeven.ets_proyect.Ets_Proyect.services;

import com.zeven.ets_proyect.Ets_Proyect.dto.LoginDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;

public interface UserService {

    User findUserById(Long userId);
    LoginDTO createUser(UserDTO userDTO);
    String encryptPassword(String originalPassword);
    String login(UserDTO userDTO);
    void updateChatId(String contact, Long chatId);
    String generateToken(Long userId);
}