package com.zeven.ets_proyect.Ets_Proyect.repositories;

import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteChangedDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteSneakersRepository extends CrudRepository<FavoriteSneaker, Long> {

    @Query(value = """
        SELECT f.user_id AS userId,
               f.price AS oldPrice,
               f.name AS sneakerName,
               f.brand AS brand,
               s.link AS link,
               s.price AS newPrice,
               u.name AS userName,
               u.chat_id AS chatId
        FROM favorite f
        JOIN sneakers s ON f.origin_id = s.origin_id
        JOIN users u ON f.user_id = u.user_id
        WHERE s.price < f.price
        ORDER BY (f.price - s.price) DESC
        LIMIT 1
        """, nativeQuery = true)
    List<FavoriteChangedDTO> findTopFavoriteChanged();

    @Query(value = """
        SELECT f.user_id AS userId,
               f.price AS oldPrice,
               f.name AS sneakerName,
               f.brand AS brand,
               s.link AS link,
               s.price AS newPrice,
               u.name AS userName,
               u.chat_id AS chatId
        FROM favorite f
        JOIN sneakers s ON f.origin_id = s.origin_id
        JOIN users u ON f.user_id = u.user_id
        WHERE s.price < f.price
          AND (LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.brand) LIKE LOWER(CONCAT('%', :query, '%')))
        ORDER BY (f.price - s.price) DESC
        """, nativeQuery = true)
    List<FavoriteChangedDTO> findFavoriteChangedByName(@Param("query") String query);

    @Query(value = """
        SELECT f.user_id AS userId,
               f.price AS oldPrice,
               f.name AS sneakerName,
               f.brand AS brand,
               s.link AS link,
               s.price AS newPrice,
               u.name AS userName,
               u.chat_id AS chatId
        FROM favorite f
        JOIN sneakers s ON f.origin_id = s.origin_id
        JOIN users u ON f.user_id = u.user_id
        WHERE s.price < f.price
          AND s.price >= :minPrice
          AND s.price <= :maxPrice
        ORDER BY (f.price - s.price) DESC
        """, nativeQuery = true)
    List<FavoriteChangedDTO> findFavoriteChangedByPriceRange(@Param("minPrice") Integer minPrice,
                                                             @Param("maxPrice") Integer maxPrice);

    List<FavoriteSneaker> findByBrandContainingIgnoreCase(String brand);
    List<FavoriteSneaker> findByPriceBetween(Integer minPrice, Integer maxPrice);

}
