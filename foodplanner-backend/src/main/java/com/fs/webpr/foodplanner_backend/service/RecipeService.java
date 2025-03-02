package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.mapper.RecipeMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import com.fs.webpr.foodplanner_backend.entity.model.RecipeDTO;
import com.fs.webpr.foodplanner_backend.exception.ResourceNotFoundException;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import com.fs.webpr.foodplanner_backend.repository.KitchenRepository;
import com.fs.webpr.foodplanner_backend.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final KitchenRepository kitchenRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeMapper recipeMapper;

    public Recipe add(RecipeDTO recipeDTO) {
        UUID kitchenId = recipeDTO.getKitchenId();
        Set<UUID> ingredientIds = recipeDTO.getIngredientIds();

        Recipe recipe = recipeMapper.toRecipe(recipeDTO);

        Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(
                () -> new ResourceNotFoundException("Kitchen not found with id " + kitchenId)
        );

        recipe.setKitchen(kitchen);

        Set<Ingredient> ingredients = recipe.getIngredients();

        ingredientIds.forEach(ingredientId -> {
            Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                    () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
            );

            ingredients.add(ingredient);
        });

        recipe.setIngredients(ingredients);

        return recipeRepository.save(recipe);
    }
}
