package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.common.annotation.CurrentUser;
import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
import com.fs.webpr.foodplanner_backend.entity.dto.request.MealPlanRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.MealPlanResponseDTO;
import com.fs.webpr.foodplanner_backend.service.MealPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mealplan")
@RequiredArgsConstructor
@Tag(name = "Meal Plan")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    @GetMapping
    @Operation(
            operationId = "getAllMealPlans",
            summary = "Retrieves a list of all meal plans"
    )
    @PreAuthorize("isAuthenticated()")
    public List<MealPlanResponseDTO> getAll(
            @CurrentUser AuthenticatedUser user
    ) {
        return mealPlanService.getAll(user);
    }

    @PostMapping
    @Operation(
            operationId = "addMealPlan",
            summary = "Creates a new meal plan"
    )
    @PreAuthorize("isAuthenticated()")
    public MealPlanResponseDTO add(
            @CurrentUser AuthenticatedUser user,
            @RequestBody MealPlanRequestDTO mealPlanRequestDTO
    ) {
        return mealPlanService.add(user, mealPlanRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getMealPlan",
            summary = "Retrieves a meal plan by id"
    )
    @PreAuthorize("isAuthenticated()")
    public MealPlanResponseDTO get(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        return mealPlanService.get(user, id);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateMealPlan",
            summary = "Updates a meal plan by id"
    )
    @PreAuthorize("isAuthenticated()")
    public MealPlanResponseDTO update(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id,
            @RequestBody MealPlanRequestDTO mealPlanRequestDTO
    ) {
        return mealPlanService.update(user, id, mealPlanRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteMealPlan",
            summary = "Deletes a meal plan by id"
    )
    @PreAuthorize("isAuthenticated()")
    public void delete(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        mealPlanService.delete(user, id);
    }
}
