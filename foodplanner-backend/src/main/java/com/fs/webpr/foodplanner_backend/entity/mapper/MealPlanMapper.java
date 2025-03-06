package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dao.MealPlanDAO;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import com.fs.webpr.foodplanner_backend.entity.dto.MealPlanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealPlanMapper {

    MealPlan toMealPlan(MealPlanDTO mealPlanDTO);

    MealPlanDAO toDAO(MealPlan mealPlan);
}
