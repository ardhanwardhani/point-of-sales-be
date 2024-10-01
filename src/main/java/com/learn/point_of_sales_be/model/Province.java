package com.learn.point_of_sales_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "provinces")
public class Province {
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Regency> regencies;
}
