package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dao.ShoppingListDAO;
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

    public List<ShoppingListDAO> getAll() {
        List<ShoppingList> shoppingLists = shoppingListRepository.findAll();

        return shoppingLists.stream().map(shoppingListMapper::toDAO).toList();
    }

    public ShoppingListDAO add(ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = shoppingListMapper.toShoppingList(shoppingListDTO);

        UUID ingredientId = shoppingListDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new RuntimeException("Ingredient not found with id " + ingredientId)
        );

        shoppingList.setIngredient(ingredient);

        shoppingList = shoppingListRepository.save(shoppingList);

        return shoppingListMapper.toDAO(shoppingList);
    }

    public ShoppingListDAO get(UUID id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Shopping List not found with id " + id)
        );

        return shoppingListMapper.toDAO(shoppingList);
    }

    public ShoppingListDAO update(UUID id, ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Shopping List not found with id " + id)
        );

        UUID ingredientId = shoppingListDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new RuntimeException("Ingredient not found with id " + ingredientId)
        );

        shoppingList.setIngredient(ingredient);

        shoppingList = shoppingListRepository.save(shoppingList);

        return shoppingListMapper.toDAO(shoppingList);
    }

    public void delete(UUID id) {
        shoppingListRepository.deleteById(id);
    }
}
