package com.zeven.ets_proyect.Ets_Proyect.repositories;

import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteChangedDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteSneakersRepository extends CrudRepository<FavoriteSneaker, Long> {

    @Query(value = """
        SELECT f.user_id AS userId,
               f.price AS oldPrice,
               f.name AS sneakerName,
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
}
