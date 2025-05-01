package com.zeven.ets_proyect.Ets_Proyect.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "available_sizes")
public class AvailableSizesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id", nullable = false)
    private Long sizeId;

    private String scale;

    private Integer type;

    private String sizes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sneaker_id", nullable = false)
    private SneakerEntity sneaker;
}
