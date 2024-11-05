package com.learn.point_of_sales_be.repository;

import com.learn.point_of_sales_be.model.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcurementRepository extends JpaRepository<Procurement, UUID> {
}
