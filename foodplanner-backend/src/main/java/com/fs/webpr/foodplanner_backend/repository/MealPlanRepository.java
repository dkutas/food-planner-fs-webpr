package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealPlanRepository extends JpaRepository<MealPlan, UUID> {
}
