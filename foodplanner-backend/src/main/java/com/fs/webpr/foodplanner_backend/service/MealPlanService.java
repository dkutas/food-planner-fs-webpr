package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dao.MealPlanDAO;
import com.fs.webpr.foodplanner_backend.entity.mapper.MealPlanMapper;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import com.fs.webpr.foodplanner_backend.entity.dto.MealPlanDTO;
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

    public List<MealPlanDAO> getAll() {
        List<MealPlan> mealPlans = mealPlanRepository.findAll();

        return mealPlans.stream().map(mealPlanMapper::toDAO).toList();
    }

    public MealPlanDAO add(MealPlanDTO mealPlanDTO) {
        UUID recipeId = mealPlanDTO.getRecipeId();

        MealPlan mealPlan = mealPlanMapper.toMealPlan(mealPlanDTO);

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + recipeId)
        );

        mealPlan.setRecipe(recipe);

        mealPlan = mealPlanRepository.save(mealPlan);

        return mealPlanMapper.toDAO(mealPlan);
    }

    public MealPlanDAO get(UUID id) {
        MealPlan mealPlan = mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        );

        return mealPlanMapper.toDAO(mealPlan);
    }

    public MealPlanDAO update(UUID id, MealPlanDTO mealPlanDTO) {
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

        mealPlan = mealPlanRepository.save(mealPlan);

        return mealPlanMapper.toDAO(mealPlan);
    }

    public void delete(UUID id) {
        mealPlanRepository.deleteById(id);
    }
}
