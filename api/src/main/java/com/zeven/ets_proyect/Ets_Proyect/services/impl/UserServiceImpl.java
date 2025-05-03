package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.FavoriteSneakersRepository;
import com.zeven.ets_proyect.Ets_Proyect.repositories.UserRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import com.zeven.ets_proyect.Ets_Proyect.utils.JwtUtil;
import com.zeven.ets_proyect.Ets_Proyect.utils.Telegram;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final FavoriteSneakersRepository favoriteSneakersRepository;
    private final Telegram telegram;

    UserServiceImpl(UserRepository userRepository,
                    ModelMapper mapper,
                    PasswordEncoder passwordEncoder,
                    JwtUtil jwtUtil,
                    FavoriteSneakersRepository favoriteSneakersRepository,
                    Telegram telegram){
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.favoriteSneakersRepository = favoriteSneakersRepository;
        this.telegram = telegram;
    }

    @Override
    @Transactional
    public String createUser(final UserDTO userDTO) {
        String encryptedPassword = this.encryptPassword(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);
        String userName = userDTO.getName();
        if (!this.isValid(userName)){
            throw new RuntimeException(ApiMessage.USER_NAME_NOT_PERMITTED);
        }
        String userContact = this.telegram.generateContact(userName);
        String urlChatBot = this.telegram.generateRegisterUrl(userContact);
        userDTO.setContact(userContact);
        userDTO.setUrlChatBot(urlChatBot);
        this.userRepository.save(mapper.map(userDTO, User.class));

        return urlChatBot;
    }

    private boolean isValid(String userName) {
        String regex = "^[a-zA-Z0-9._-]+$";
        return userName != null && userName.matches(regex) && userName.length() <= 8;
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

        return bearerPrefix + this.jwtUtil.generateToken(userFound.getIdUser());
    }

    @Override
    public void addFavoriteToUser(FavoriteSneakerDTO favoriteSneakerDTO) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = this.userRepository.findByIdUser(userId).orElseThrow(() ->
                new RuntimeException(ApiMessage.USER_NOT_FOUND));

        FavoriteSneaker favoriteSneaker = this.mapper.map(favoriteSneakerDTO, FavoriteSneaker.class);
        favoriteSneaker.setUser(user);
        this.favoriteSneakersRepository.save(favoriteSneaker);
    }

    @Override
    public void deleteFavoriteById(Long favoriteSneakerId) {
        FavoriteSneaker favoriteSneaker = this.favoriteSneakersRepository.findById(favoriteSneakerId).orElseThrow(() ->
                new RuntimeException(ApiMessage.FAVORITE_NOT_FOUND));
        this.favoriteSneakersRepository.delete(favoriteSneaker);
    }

    @Override
    public void updateChatId(String contact, Long chatId) {
        User userEntity = this.userRepository.findByContact(contact).orElseThrow(() ->
                new RuntimeException(ApiMessage.CONTACT_NOT_FOUND));

        userEntity.setChatId(chatId);
        this.userRepository.save(userEntity);
    }
}
