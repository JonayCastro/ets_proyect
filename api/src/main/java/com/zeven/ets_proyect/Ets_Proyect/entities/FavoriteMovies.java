package com.zeven.ets_proyect.Ets_Proyect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorite_movies")
public class FavoriteMovies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite", nullable = false)
    private Long idFavorite;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = true)
    private String description;

    @ManyToMany(mappedBy = "favorites")
    private Set<User> users = new HashSet<>();


}
