package com.learn.point_of_sales_be.repository;

import com.learn.point_of_sales_be.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
}
