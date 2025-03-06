package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dao.RecipeDAO;
import com.fs.webpr.foodplanner_backend.entity.mapper.RecipeMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import com.fs.webpr.foodplanner_backend.entity.dto.RecipeDTO;
import com.fs.webpr.foodplanner_backend.exception.ResourceNotFoundException;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import com.fs.webpr.foodplanner_backend.repository.KitchenRepository;
import com.fs.webpr.foodplanner_backend.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@SuppressWarnings("Duplicates")
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final KitchenRepository kitchenRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeDAO> getAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipeMapper::toDAO).toList();
    }

    public RecipeDAO add(RecipeDTO recipeDTO) {
        UUID kitchenId = recipeDTO.getKitchenId();
        Set<UUID> ingredientIds = recipeDTO.getIngredientIds();

        Recipe recipe = recipeMapper.toRecipe(recipeDTO);

        Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(
                () -> new ResourceNotFoundException("Kitchen not found with id " + kitchenId)
        );

        recipe.setKitchen(kitchen);

        Set<Ingredient> ingredients = new HashSet<>(ingredientRepository.findAllById(ingredientIds));

        Set<UUID> foundIngredientIds = ingredients.stream().map(Ingredient::getId).collect(Collectors.toSet());

        ingredientIds.removeAll(foundIngredientIds);

        if (!ingredientIds.isEmpty()) {
            throw new ResourceNotFoundException("Ingredient not found with id " + ingredientIds);
        }

        recipe.setIngredients(ingredients);

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDAO(recipe);
    }

    public RecipeDAO get(UUID id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + id)
        );

        return recipeMapper.toDAO(recipe);
    }

    public RecipeDAO update(UUID id, RecipeDTO recipeDTO) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + id)
        );

        UUID kitchenId = recipeDTO.getKitchenId();
        Set<UUID> ingredientIds = recipeDTO.getIngredientIds();

        Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(
                () -> new ResourceNotFoundException("Kitchen not found with id " + kitchenId)
        );

        recipe.setKitchen(kitchen);

        Set<Ingredient> ingredients = recipe.getIngredients();

        ingredients.clear();

        ingredients.addAll(ingredientRepository.findAllById(ingredientIds));

        Set<UUID> foundIngredientIds = ingredients.stream().map(Ingredient::getId).collect(Collectors.toSet());

        ingredientIds.removeAll(foundIngredientIds);

        if (!ingredientIds.isEmpty()) {
            throw new ResourceNotFoundException("Ingredient not found with id " + ingredientIds);
        }

        recipe.setIngredients(ingredients);

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDAO(recipe);
    }

    public void delete(UUID id) {
        recipeRepository.deleteById(id);
    }
}
