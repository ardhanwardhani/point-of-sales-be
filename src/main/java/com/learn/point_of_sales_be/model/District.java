package com.learn.point_of_sales_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "districts")
public class District {
    @Id
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "regency_id", nullable = false)
    private Regency regency;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Village> villages;
}
