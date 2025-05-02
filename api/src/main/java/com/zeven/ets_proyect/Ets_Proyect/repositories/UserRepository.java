package com.zeven.ets_proyect.Ets_Proyect.repositories;

import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String userName);
    Optional<User> findByIdUser(Long idUser);
    Optional<User> findByContact(String contact);
}
