package com.learn.point_of_sales_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductVariantOptionRepository extends JpaRepository<ProductVariantOptionRepository, UUID> {
}
