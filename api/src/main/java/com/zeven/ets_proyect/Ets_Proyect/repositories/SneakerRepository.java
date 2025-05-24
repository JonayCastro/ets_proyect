package com.zeven.ets_proyect.Ets_Proyect.repositories;

import com.zeven.ets_proyect.Ets_Proyect.entities.SneakerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SneakerRepository extends CrudRepository<SneakerEntity, Long> {

    @Query("SELECT s FROM SneakerEntity s WHERE s.originId NOT IN (SELECT f.originId FROM FavoriteSneaker f)")
    List<SneakerEntity> findSneakersNotInFavorites();
}
