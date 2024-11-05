package com.learn.point_of_sales_be.repository;

import com.learn.point_of_sales_be.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
