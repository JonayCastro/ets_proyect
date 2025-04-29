package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.dto.SneakersResponseDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.UserRepository;
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

    UserServiceImpl(UserRepository userRepository,
                    ModelMapper mapper,
                    SupplierCatalogServices supplierCatalogServices,
                    PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.supplierCatalogServices = supplierCatalogServices;
        this.passwordEncoder = passwordEncoder;
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
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(userDTO.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return this.supplierCatalogServices.getSneakerList();
    }

}
