package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.request.RecipeRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.RecipeResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.mapper.RecipeMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import com.fs.webpr.foodplanner_backend.exception.ResourceNotFoundException;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import com.fs.webpr.foodplanner_backend.repository.KitchenRepository;
import com.fs.webpr.foodplanner_backend.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("Duplicates")
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final KitchenRepository kitchenRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeResponseDTO> getAll() {
        return recipeMapper.toRecipeResponseDTO(recipeRepository.findAll());
    }

    public RecipeResponseDTO add(RecipeRequestDTO recipeRequestDTO) {
        UUID kitchenId = recipeRequestDTO.getKitchenId();
        Set<UUID> ingredientIds = recipeRequestDTO.getIngredientIds();

        Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(
                () -> new ResourceNotFoundException("Kitchen not found with id " + kitchenId)
        );

        log.debug("Kitchen Found: {}", kitchen);

        Set<Ingredient> ingredients = new HashSet<>(ingredientRepository.findAllById(ingredientIds));

        log.debug("Ingredients Found: {}", ingredients);

        Set<UUID> foundIngredientIds = ingredients.stream().map(Ingredient::getId).collect(Collectors.toSet());

        ingredientIds.removeAll(foundIngredientIds);

        log.debug("Not Found Ingredients: {}", ingredientIds);

        if (!ingredientIds.isEmpty()) {
            throw new ResourceNotFoundException("Ingredient not found with id " + ingredientIds);
        }

        Recipe recipe = new Recipe();

        recipe.setKitchen(kitchen);

        recipe.setIngredients(ingredients);

        log.debug("Recipe With Ingredients: {}", recipe);

        return recipeMapper.toRecipeResponseDTO(recipeRepository.save(recipe));
    }

    public RecipeResponseDTO get(UUID id) {
        return recipeMapper.toRecipeResponseDTO(recipeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + id)
        ));
    }

    public RecipeResponseDTO update(UUID id, RecipeRequestDTO recipeRequestDTO) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id " + id)
        );

        log.debug("Recipe Found: {}", recipe);

        if (recipeRequestDTO.getName() != null) {
            recipe.setName(recipeRequestDTO.getName());
        }

        if (recipeRequestDTO.getDescription() != null) {
            recipe.setDescription(recipeRequestDTO.getDescription());
        }

        UUID kitchenId = recipeRequestDTO.getKitchenId();
        Set<UUID> ingredientIds = recipeRequestDTO.getIngredientIds();

        if (kitchenId != null) {
            Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(
                    () -> new ResourceNotFoundException("Kitchen not found with id " + kitchenId)
            );

            log.debug("Found Kitchen: {}", kitchen);

            recipe.setKitchen(kitchen);
        }

        if (ingredientIds != null) {
            Set<Ingredient> ingredients = recipe.getIngredients();

            ingredients.clear();

            ingredients.addAll(ingredientRepository.findAllById(ingredientIds));

            Set<UUID> foundIngredientIds = ingredients.stream().map(Ingredient::getId).collect(Collectors.toSet());

            ingredientIds.removeAll(foundIngredientIds);

            log.debug("Ingredients Not Found: {}", ingredientIds);

            if (!ingredientIds.isEmpty()) {
                throw new ResourceNotFoundException("Ingredient not found with id " + ingredientIds);
            }

            recipe.setIngredients(ingredients);
        }

        return recipeMapper.toRecipeResponseDTO(recipeRepository.save(recipe));
    }

    public void delete(UUID id) {
        boolean isRecipeExists = recipeRepository.existsById(id);

        if (!isRecipeExists) {
            throw new ResourceNotFoundException("Meal Plan not found with id " + id);
        }

        recipeRepository.deleteById(id);
    }
}
