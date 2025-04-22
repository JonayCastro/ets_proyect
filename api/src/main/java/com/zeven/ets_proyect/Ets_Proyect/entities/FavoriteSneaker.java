package com.zeven.ets_proyect.Ets_Proyect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    private String name;

    private String brand;

    private String size;

    private String color;

    private Integer price;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany(mappedBy = "favorites")
    private Set<User> users = new HashSet<>();


}
