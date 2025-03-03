package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlanDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealPlanMapper {

    MealPlan toMealPlan(MealPlanDTO mealPlanDTO);
}
