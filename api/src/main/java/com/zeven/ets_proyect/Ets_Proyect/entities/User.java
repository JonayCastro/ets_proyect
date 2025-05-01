package com.zeven.ets_proyect.Ets_Proyect.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long idUser;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FavoriteSneaker> favorites = new HashSet<>();
}
