package com.learn.point_of_sales_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "regencies")
public class Regency {
    @Id
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "regency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<District> districts;
}
