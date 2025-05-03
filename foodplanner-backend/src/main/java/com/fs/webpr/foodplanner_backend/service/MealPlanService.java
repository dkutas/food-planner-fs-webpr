package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.request.MealPlanRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.MealPlanResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.mapper.MealPlanMapper;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import com.fs.webpr.foodplanner_backend.exception.ResourceNotFoundException;
import com.fs.webpr.foodplanner_backend.repository.MealPlanRepository;
import com.fs.webpr.foodplanner_backend.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;
    private final RecipeRepository recipeRepository;
    private final MealPlanMapper mealPlanMapper;

    public List<MealPlanResponseDTO> getAll() {
        return mealPlanMapper.toMealPlanResponseDTO(mealPlanRepository.findAll());
    }

    public MealPlanResponseDTO add(MealPlanRequestDTO mealPlanRequestDTO) {
        UUID recipeId = mealPlanRequestDTO.getRecipeId();

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + recipeId)
        );

        MealPlan mealPlan = new MealPlan();

        mealPlan.setRecipe(recipe);

        return mealPlanMapper.toMealPlanResponseDTO(mealPlanRepository.save(mealPlan));
    }

    public MealPlanResponseDTO get(UUID id) {
        return mealPlanMapper.toMealPlanResponseDTO(mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        ));
    }

    public MealPlanResponseDTO update(UUID id, MealPlanRequestDTO mealPlanRequestDTO) {
        MealPlan mealPlan = mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        );

        if (mealPlanRequestDTO.getRecipeId() != null) {
            UUID recipeId = mealPlanRequestDTO.getRecipeId();

            Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                    () -> new ResourceNotFoundException("Recipe not found with id " + recipeId)
            );

            mealPlan.setRecipe(recipe);
        }

        if (mealPlanRequestDTO.getStartDate() != null) {
            mealPlan.setStartDate(mealPlanRequestDTO.getStartDate());
        }

        if (mealPlanRequestDTO.getEndDate() != null) {
            mealPlan.setEndDate(mealPlanRequestDTO.getEndDate());
        }

        return mealPlanMapper.toMealPlanResponseDTO(mealPlanRepository.save(mealPlan));
    }

    public void delete(UUID id) {
        boolean isMealPlanExists = mealPlanRepository.existsById(id);

        if (!isMealPlanExists) {
            throw new ResourceNotFoundException("Meal Plan not found with id " + id);
        }

        mealPlanRepository.deleteById(id);
    }
}
