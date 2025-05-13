package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.common.annotation.CurrentUser;
import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
import com.fs.webpr.foodplanner_backend.entity.dto.response.IngredientResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.MissingIngredientByMealResponseDTO;
import com.fs.webpr.foodplanner_backend.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    @Operation(
            operationId = "getAllIngredient",
            summary = "Retrieves a list of all ingredients"
    )
    public List<IngredientResponseDTO> getAll() {
        return ingredientService.getAll();
    }

    @GetMapping("/missing/pantry")
    @Operation(
            operationId = "getAllIngredientNotInPantry",
            summary = "Retrieves all the ingredients that are not already in pantry for the current user"
    )
    @PreAuthorize("isAuthenticated()")
    public List<IngredientResponseDTO> getIngredientsNotInPantry(
            @CurrentUser AuthenticatedUser user
    ) {
        return ingredientService.getAllIngredientNotInPantry(user);
    }

    @GetMapping("/missing/shoppinglist")
    @Operation(
            operationId = "getAllIngredientNotOnShoppingList",
            summary = "Retrieves all the ingredients that are not already on the shopping list of the current user"
    )
    @PreAuthorize("isAuthenticated()")
    public List<IngredientResponseDTO> getIngredientsNotOnShoppingList(
            @CurrentUser AuthenticatedUser user
    ) {
        return ingredientService.getAllIngredientNotOnShoppingList(user);
    }

    //TODO: Might break if date is not provided
    @GetMapping("/missing/mealplan")
    @Operation(
            operationId = "getAllIngredientMissingByMeal",
            summary = "Retrieves all the ingredients by meal that are missing for the planned meals of the current user"
    )
    @PreAuthorize("isAuthenticated()")
    public List<MissingIngredientByMealResponseDTO> getAllIngredientMissingByMeal(
            @CurrentUser AuthenticatedUser user,
            @RequestParam("startDate") OffsetDateTime startDate,
            @RequestParam("endDate") OffsetDateTime endDate
    ) {
        return ingredientService.getAllIngredientMissingByMeal(user, startDate, endDate);
    }

}
