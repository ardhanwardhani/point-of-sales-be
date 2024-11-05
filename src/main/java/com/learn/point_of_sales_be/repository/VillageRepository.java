package com.learn.point_of_sales_be.repository;

import com.learn.point_of_sales_be.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageRepository extends JpaRepository<Village, Integer> {
}
