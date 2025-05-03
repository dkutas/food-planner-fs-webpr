package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dto.request.MealPlanRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.MealPlanResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealPlanMapper {

    MealPlan toMealPlan(MealPlanRequestDTO mealPlanRequestDTO);

    MealPlanResponseDTO toMealPlanResponseDTO(MealPlan mealPlan);

    List<MealPlanResponseDTO> toMealPlanResponseDTO(List<MealPlan> mealPlans);
}
