package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
import com.fs.webpr.foodplanner_backend.entity.dto.request.ShoppingListRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.ShoppingListResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.mapper.ShoppingListMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import com.fs.webpr.foodplanner_backend.exception.ResourceNotFoundException;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import com.fs.webpr.foodplanner_backend.repository.ShoppingListRepository;
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
@SuppressWarnings("Duplicates")
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final IngredientRepository ingredientRepository;
    private final ShoppingListMapper shoppingListMapper;

    public List<ShoppingListResponseDTO> getAll(AuthenticatedUser user) {
        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.findAllByUserId(user.userId()));
    }

    public ShoppingListResponseDTO add(AuthenticatedUser user, ShoppingListRequestDTO shoppingListRequestDTO) {
        UUID ingredientId = shoppingListRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        log.debug("Found Ingredient: {}", ingredient);

        ShoppingList shoppingList = new ShoppingList();

        shoppingList.setUserId(user.userId());
        shoppingList.setIngredient(ingredient);

        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.save(shoppingList));
    }

    public ShoppingListResponseDTO get(AuthenticatedUser user, UUID id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Shopping List not found with id " + id)
        );

        if (!shoppingList.getUserId().equals(user.userId())) {
            throw new AccessDeniedException("You do not have permission to get shopping list item with id " + id);
        }

        return shoppingListMapper.toShoppingListResponseDTO(shoppingList);
    }

    public List<ShoppingListResponseDTO> getAllByIngredientId(AuthenticatedUser user, UUID ingredientId) {
        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.findAllByUserIdAndIngredient_Id(user.userId(), ingredientId));
    }

    public ShoppingListResponseDTO update(AuthenticatedUser user, UUID id, ShoppingListRequestDTO shoppingListRequestDTO) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Shopping List not found with id " + id)
        );

        if (!shoppingList.getUserId().equals(user.userId())) {
            throw new AccessDeniedException("You do not have permission to update shopping list item with id " + id);
        }

        UUID ingredientId = shoppingListRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        shoppingList.setIngredient(ingredient);

        return shoppingListMapper.toShoppingListResponseDTO(shoppingListRepository.save(shoppingList));
    }

    public void delete(AuthenticatedUser user, UUID id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Shopping List Item not found with id " + id)
        );

        if (!shoppingList.getUserId().equals(user.userId())) {
            throw new AccessDeniedException("You do not have permission to delete shopping list item with id " + id);
        }

        shoppingListRepository.deleteById(id);
    }

}
