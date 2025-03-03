package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.mapper.ShoppingListMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import com.fs.webpr.foodplanner_backend.entity.dto.ShoppingListDTO;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import com.fs.webpr.foodplanner_backend.repository.ShoppingListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final IngredientRepository ingredientRepository;
    private final ShoppingListMapper shoppingListMapper;

    public List<ShoppingList> getAll() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList add(ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = shoppingListMapper.toShoppingList(shoppingListDTO);

        UUID ingredientId = shoppingListDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new RuntimeException("Ingredient not found with id " + ingredientId)
        );

        shoppingList.setIngredient(ingredient);

        return shoppingListRepository.save(shoppingList);
    }

    public ShoppingList get(UUID id) {
        return shoppingListRepository.findById(id).orElseThrow();
    }

    public ShoppingList update(UUID id, ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Shopping List not found with id " + id)
        );

        UUID ingredientId = shoppingListDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new RuntimeException("Ingredient not found with id " + ingredientId)
        );

        shoppingList.setIngredient(ingredient);

        return shoppingListRepository.save(shoppingList);
    }

    public void delete(UUID id) {
        shoppingListRepository.deleteById(id);
    }
}
