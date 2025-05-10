package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;
    private final RecipeRepository recipeRepository;
    private final MealPlanMapper mealPlanMapper;

    public List<MealPlanResponseDTO> getAll(AuthenticatedUser user) {
        return mealPlanMapper.toMealPlanResponseDTO(mealPlanRepository.findAllByUserId(user.userId()));
    }

    public MealPlanResponseDTO add(AuthenticatedUser user, MealPlanRequestDTO mealPlanRequestDTO) {
        UUID recipeId = mealPlanRequestDTO.getRecipeId();

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + recipeId)
        );

        MealPlan mealPlan = new MealPlan();

        mealPlan.setUserId(user.userId());
        mealPlan.setRecipe(recipe);

        return mealPlanMapper.toMealPlanResponseDTO(mealPlanRepository.save(mealPlan));
    }

    public MealPlanResponseDTO get(AuthenticatedUser user, UUID id) {
        MealPlan mealPlan = mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        );

        if (mealPlan.getUserId() != user.userId()) {
            throw new AccessDeniedException("You do not have permission to get meal plan with id " + id);
        }

        return mealPlanMapper.toMealPlanResponseDTO(mealPlan);
    }

    public MealPlanResponseDTO update(AuthenticatedUser user, UUID id, MealPlanRequestDTO mealPlanRequestDTO) {
        MealPlan mealPlan = mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        );

        if (mealPlan.getUserId() != user.userId()) {
            throw new AccessDeniedException("You do not have permission to update meal plan with id " + id);
        }

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

    public void delete(AuthenticatedUser user, UUID id) {
        MealPlan mealPlan = mealPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal Plan not found with id " + id)
        );

        if (mealPlan.getUserId() != user.userId()) {
            throw new AccessDeniedException("You do not have permission to get meal plan with id " + id);
        }

        mealPlanRepository.deleteById(id);
    }
}
