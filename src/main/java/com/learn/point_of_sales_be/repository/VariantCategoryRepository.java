package com.learn.point_of_sales_be.repository;

import com.learn.point_of_sales_be.model.VariantCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantCategoryRepository extends JpaRepository<VariantCategory, UUID> {
}
