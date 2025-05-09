package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.request.ShoppingListRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.ShoppingListResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.mapper.ShoppingListMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import com.fs.webpr.foodplanner_backend.exception.AlreadyExistsException;
import com.fs.webpr.foodplanner_backend.exception.ResourceNotFoundException;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import com.fs.webpr.foodplanner_backend.repository.ShoppingListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("Duplicates")
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final IngredientRepository ingredientRepository;
    private final ShoppingListMapper shoppingListMapper;

    public List<ShoppingListResponseDTO> getAll() {
        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.findAll());
    }

    public ShoppingListResponseDTO add(ShoppingListRequestDTO shoppingListRequestDTO) {
        UUID ingredientId = shoppingListRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        log.debug("Found Ingredient: {}", ingredient);

        boolean isShoppingListIngredientAlreadyExists = shoppingListRepository.existsByIngredient_Id(ingredientId);

        if (isShoppingListIngredientAlreadyExists) {
            throw new AlreadyExistsException("Ingredient with id " + ingredientId + " is already in pantry");
        }

        ShoppingList shoppingList = new ShoppingList();

        shoppingList.setIngredient(ingredient);

        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.save(shoppingList));
    }

    public ShoppingListResponseDTO get(UUID id) {
        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Shopping List not found with id " + id)
        ));
    }

    public ShoppingListResponseDTO getByIngredientId(UUID ingredientId) {
        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.findByIngredient_Id(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Shopping List Item not found with ingredient id " + ingredientId)
        ));
    }

    public ShoppingListResponseDTO update(UUID id, ShoppingListRequestDTO shoppingListRequestDTO) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Shopping List not found with id " + id)
        );

        UUID ingredientId = shoppingListRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        boolean isShoppingListIngredientAlreadyExists = shoppingListRepository.existsByIngredient_Id(ingredientId);

        if (isShoppingListIngredientAlreadyExists) {
            throw new AlreadyExistsException("Ingredient with id " + ingredientId + " is already in pantry");
        }

        shoppingList.setIngredient(ingredient);

        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.save(shoppingList));
    }

    public void delete(UUID id) {
        boolean isShoppingListItemExists = shoppingListRepository.existsById(id);

        if (!isShoppingListItemExists) {
            throw new ResourceNotFoundException("Shopping List Item not found with id " + id);
        }

        shoppingListRepository.deleteById(id);
    }

    public void deleteByIngredientId(UUID ingredientId) {

        boolean isShoppingListIngredientExists = shoppingListRepository.existsByIngredient_Id(ingredientId);

        if (!isShoppingListIngredientExists) {
            throw new ResourceNotFoundException("Shopping List Ingredient not found with id " + ingredientId);
        }

        shoppingListRepository.deleteByIngredient_Id(ingredientId);
    }
}
