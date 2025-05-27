package com.zeven.ets_proyect.Ets_Proyect.repositories;

import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import com.zeven.ets_proyect.Ets_Proyect.entities.SneakerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SneakerRepository extends CrudRepository<SneakerEntity, Long> {

    @Query("SELECT s FROM SneakerEntity s WHERE s.originId NOT IN (SELECT f.originId FROM FavoriteSneaker f)")
    List<SneakerEntity> findSneakersNotInFavorites();

    @Query("""
      SELECT s
      FROM SneakerEntity s
      WHERE LOWER(s.brand) LIKE LOWER(CONCAT('%', :brand, '%'))
      AND s.originId NOT IN (
        SELECT f.originId FROM FavoriteSneaker f
      )
    """)
    List<SneakerEntity> findSneakersByBrandExcludingFavorites(@Param("brand") String brand);

    @Query("""
      SELECT s
      FROM SneakerEntity s
      WHERE s.price BETWEEN :minPrice AND :maxPrice
      AND s.originId NOT IN (
        SELECT f.originId FROM FavoriteSneaker f
      )
    """)
    List<SneakerEntity> findSneakersByPriceRangeExcludingFavorites(
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice
    );
}
