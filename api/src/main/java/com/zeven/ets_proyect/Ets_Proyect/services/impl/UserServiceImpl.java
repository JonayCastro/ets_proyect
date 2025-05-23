package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.dto.LoginDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.UserRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import com.zeven.ets_proyect.Ets_Proyect.utils.JwtUtil;
import com.zeven.ets_proyect.Ets_Proyect.utils.Telegram;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final Telegram telegram;

    UserServiceImpl(UserRepository userRepository,
                    ModelMapper mapper,
                    PasswordEncoder passwordEncoder,
                    JwtUtil jwtUtil,
                    Telegram telegram){
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.telegram = telegram;
    }

    @Override
    public User findUserById(Long userId) {
        return this.userRepository.findByIdUser(userId).orElseThrow(() ->
                new RuntimeException(ApiMessage.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public LoginDTO createUser(final UserDTO userDTO) {
        /**
         * This method creates a new user in the system.
         * It first checks if the user already exists in the database.
         * If the user does not exist, it encrypts the password and generates a contact and URL for the user.
         * Finally, it saves the user entity to the database and generates a token for the user.
         *
         * @param userDTO The UserDTO object containing the user's information.
         * * @return A LoginDTO object containing the generated token and URL for the user.
         */
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
        User userEntity = this.userRepository.save(mapper.map(userDTO, User.class));

        String token = this.generateToken(userEntity.getIdUser());

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken(token);
        loginDTO.setUrl(urlChatBot);

        return loginDTO;
    }

    private boolean isValid(String userName) {
        String regex = "^[a-zA-Z0-9._-]+$";
        return userName != null && userName.matches(regex) && userName.length() <= 8;
    }

    @Override
    public String encryptPassword(String originalPassword) {
        /**
         * This method encrypts the original password using the password encoder.
         * It is used when creating a new user to ensure that the password is stored securely in the database.
         *
         * @param originalPassword The original password to be encrypted.
         *
         * @return The encrypted password as a string.
         */
        return this.passwordEncoder.encode(originalPassword);
    }

    @Override
    public String login(final UserDTO userDTO) {
        /**
         * This method handles user login by checking the provided credentials against the database.
         * If the credentials are valid, it generates a token for the user.
         *
         * @param userDTO The UserDTO object containing the user's login information.
         *
         * @return A string representing the generated token for the user.
         */

        User userFound = this.userRepository.findByName(userDTO.getName())
                .orElseThrow(() -> new RuntimeException(ApiMessage.USER_NOT_FOUND));

        if (!passwordEncoder.matches(userDTO.getPassword(), userFound.getPassword())) {
            throw new RuntimeException(ApiMessage.BAD_CREDENTIALS);
        }
        return this.generateToken(userFound.getIdUser());
    }

    @Override
    public void updateChatId(String contact, Long chatId) {
        /**
         * This method updates the chat ID for a user in the database.
         * It retrieves the user from the database using the provided contact information and updates the chat ID.
         */
        User userEntity = this.userRepository.findByContact(contact).orElseThrow(() ->
                new RuntimeException(ApiMessage.CONTACT_NOT_FOUND));

        userEntity.setChatId(chatId);
        this.userRepository.save(userEntity);
    }

    @Override
    public String generateToken(Long userId) {
        /**
         * This method generates a JWT token for the user using the provided user ID.
         * The token is prefixed with "Bearer " to indicate that it is a bearer token.
         *
         * @param userId The ID of the user for whom the token is generated.
         *
         * @return A string representing the generated JWT token for the user.
         */
        String bearerPrefix = "Bearer ";
        return bearerPrefix + this.jwtUtil.generateToken(userId);
    }
}
