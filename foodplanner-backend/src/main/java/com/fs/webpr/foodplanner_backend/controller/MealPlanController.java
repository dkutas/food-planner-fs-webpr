package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.MealPlanDTO;
import com.fs.webpr.foodplanner_backend.entity.model.MealPlan;
import com.fs.webpr.foodplanner_backend.service.MealPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mealplan")
@RequiredArgsConstructor
@Tag(name = "Meal Plan")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    @GetMapping
    public List<MealPlan> getAll() {
        try {
            return mealPlanService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PostMapping
    public MealPlan add(MealPlanDTO mealPlanDTO) {
        try {
            return mealPlanService.add(mealPlanDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @GetMapping("/{id}")
    public MealPlan get(@PathVariable UUID id) {
        try {
            return mealPlanService.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PatchMapping("/{id}")
    public MealPlan update(@PathVariable UUID id, MealPlanDTO mealPlanDTO) {
        try {
            return mealPlanService.update(id, mealPlanDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        try {
            mealPlanService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }
}
