package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteCtrlDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.SneakersResponseDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.UserRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.FavoriteService;
import com.zeven.ets_proyect.Ets_Proyect.services.SupplierCatalogServices;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final SupplierCatalogServices supplierCatalogServices;
    private final PasswordEncoder passwordEncoder;
    private final FavoriteService favoriteService;

    UserServiceImpl(UserRepository userRepository,
                    ModelMapper mapper,
                    SupplierCatalogServices supplierCatalogServices,
                    PasswordEncoder passwordEncoder,
                    FavoriteService favoriteService){
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.supplierCatalogServices = supplierCatalogServices;
        this.passwordEncoder = passwordEncoder;
        this.favoriteService = favoriteService;
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
    public SneakersResponseDTO login(final UserDTO userDTO) {
        User userFound = this.userRepository.findByName(userDTO.getName())
                .orElseThrow(() -> new RuntimeException(ApiMessage.USER_NOT_FOUND));

        if (!passwordEncoder.matches(userDTO.getPassword(), userFound.getPassword())) {
            throw new RuntimeException(ApiMessage.BAD_CREDENTIALS);
        }

        return this.supplierCatalogServices.getSneakerList();
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
