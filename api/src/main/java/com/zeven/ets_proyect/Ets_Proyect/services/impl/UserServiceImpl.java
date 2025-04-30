package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteCtrlDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.UserRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.FavoriteService;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import com.zeven.ets_proyect.Ets_Proyect.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final FavoriteService favoriteService;
    private final JwtUtil jwtUtil;

    UserServiceImpl(UserRepository userRepository,
                    ModelMapper mapper,
                    PasswordEncoder passwordEncoder,
                    FavoriteService favoriteService,
                    JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.favoriteService = favoriteService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public void createUser(final UserDTO userDTO) {
        String encryptedPassword = this.encryptPassword(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);
        this.userRepository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public String encryptPassword(String originalPassword) {
        return this.passwordEncoder.encode(originalPassword);
    }

    @Override
    public String login(final UserDTO userDTO) {
        String bearerPrefix = "Bearer ";
        User userFound = this.userRepository.findByName(userDTO.getName())
                .orElseThrow(() -> new RuntimeException(ApiMessage.USER_NOT_FOUND));

        if (!passwordEncoder.matches(userDTO.getPassword(), userFound.getPassword())) {
            throw new RuntimeException(ApiMessage.BAD_CREDENTIALS);
        }

        return bearerPrefix + this.jwtUtil.generateToken(userDTO.getName());
    }

    @Override
    public void addFavoriteToUser(FavoriteCtrlDTO favoriteCtrlDTO) {
        User user = userRepository.findById(favoriteCtrlDTO.getFavoriteId())
                .orElseThrow(() -> new RuntimeException(ApiMessage.USER_NOT_FOUND));

        FavoriteSneaker favorite = this.favoriteService.getFavoriteById(favoriteCtrlDTO.getFavoriteId());
        user.getFavorites().add(favorite);
        userRepository.save(user);
    }

    @Override
    public void removeFavoriteFromUser(FavoriteCtrlDTO favoriteCtrlDTO) {
        User user = userRepository.findById(favoriteCtrlDTO.getUserId())
                .orElseThrow(() -> new RuntimeException(ApiMessage.USER_NOT_FOUND));

        FavoriteSneaker favorite = this.favoriteService.getFavoriteById(favoriteCtrlDTO.getFavoriteId());
        user.getFavorites().remove(favorite);
        userRepository.save(user);

    }
}
