package com.zeven.ets_proyect.Ets_Proyect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sneakers")
public class SneakerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sneaker_id", nullable = false)
    private Long sneakerId;

    @Column(name="origin_id")
    private Long originId;

    @Column(name = "origin_collection_id")
    private Long originCollectionId;

    @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableSizesEntity> availableSizes;

    @Column(name = "image_alt")
    private String imageAlt;

    @Column(name = "tax_rate")
    private String taxRate;

    @Column(name = "original_price")
    private Integer originalPrice;

    @Column(name = "price_base")
    private Double priceBase;

    @Column(name = "tax_amount")
    private Double taxAmount;

    private Integer price;
    private String brand;
    private String image;
    private String link;
    private String name;
    private String reference;
}
