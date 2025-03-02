package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KitchenRepository extends JpaRepository<Kitchen, UUID> {
}
