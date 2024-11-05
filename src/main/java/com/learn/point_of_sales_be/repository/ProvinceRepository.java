package com.learn.point_of_sales_be.repository;

import com.learn.point_of_sales_be.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
