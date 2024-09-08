package com.learn.point_of_sales_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "variant_category")
public class VariantCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "variantCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VariantOption> variantOptions;
}
