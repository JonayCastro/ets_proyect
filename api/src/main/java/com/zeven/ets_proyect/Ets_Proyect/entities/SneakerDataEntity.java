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
@Table(name = "suppliers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "supplier_identifier")
        })
public class SneakerDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "supplier_identifier")
    private String supplierIdentifier;

    private Integer pages;

    private Integer totalProducts;

    private Integer totalPages;
}
