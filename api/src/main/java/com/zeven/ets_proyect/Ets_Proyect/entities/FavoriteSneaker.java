package com.zeven.ets_proyect.Ets_Proyect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "favorite",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "origin_id")
        }
)
public class FavoriteSneaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id", nullable = false)
    private Long idFavorite;

    @Column(name="origin_id", unique = true, nullable = false)
    private Long originId;

    @Column(name ="origin_collection_id", nullable = false)
    private Long originCollectionId;

    private String name;

    private String brand;

    private String size;

    private Integer price;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
