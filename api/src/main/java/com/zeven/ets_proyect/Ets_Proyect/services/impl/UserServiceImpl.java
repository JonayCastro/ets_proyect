package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.dto.SneakersResponseDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.UserRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.SupplierCatalogServices;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final SupplierCatalogServices supplierCatalogServices;

    UserServiceImpl(UserRepository userRepository, ModelMapper mapper, SupplierCatalogServices supplierCatalogServices){
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.supplierCatalogServices = supplierCatalogServices;
    }

    @Override
    @Transactional
    public void createUser(UserDTO userDTO) {
        this.userRepository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public SneakersResponseDTO login(UserDTO userDTO) {
        User userFound = this.userRepository.findByNameAndPassword(userDTO.getName(), userDTO.getPassword())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return this.supplierCatalogServices.getSneakerList();

    }
}
