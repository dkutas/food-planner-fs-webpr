package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, UUID> {

    List<MealPlan> findAllByUserId(UUID userId);
}
