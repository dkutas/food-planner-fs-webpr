package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.mapper.MealPlanMapper;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlanDTO;
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

    public List<MealPlan> getAll() {
        return mealPlanRepository.findAll();
    }

    public MealPlan add(MealPlanDTO mealPlanDTO) {
        UUID recipeId = mealPlanDTO.getRecipeId();

        MealPlan mealPlan = mealPlanMapper.toMealPlan(mealPlanDTO);

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + recipeId)
        );

        mealPlan.setRecipe(recipe);

        return mealPlanRepository.save(mealPlan);
    }

    public MealPlan get(UUID id) {
        return mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        );
    }

    public MealPlan update(UUID id, MealPlanDTO mealPlanDTO) {
        MealPlan mealPlan = mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        );

        UUID recipeId = mealPlanDTO.getRecipeId();

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + recipeId)
        );

        mealPlan.setRecipe(recipe);
        mealPlan.setStartDate(mealPlanDTO.getStartDate());
        mealPlan.setEndDate(mealPlanDTO.getEndDate());

        return mealPlanRepository.save(mealPlan);
    }

    public void delete(UUID id) {
        mealPlanRepository.deleteById(id);
    }
}
