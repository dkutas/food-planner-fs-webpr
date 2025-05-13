package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
import com.fs.webpr.foodplanner_backend.entity.dto.response.IngredientResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.MissingIngredientByMealResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.mapper.IngredientMapper;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public List<IngredientResponseDTO> getAll() {
        return ingredientMapper.toIngredientResponseDTO(ingredientRepository.findAll());
    }

    public List<IngredientResponseDTO> getAllIngredientNotInPantry(AuthenticatedUser user) {
        return ingredientMapper.toIngredientResponseDTO(ingredientRepository.getIngredientsNotInPantry(user.userId()));
    }

    public List<IngredientResponseDTO> getAllIngredientNotOnShoppingList(AuthenticatedUser user) {
        return ingredientMapper.toIngredientResponseDTO(ingredientRepository.getIngredientsNotOnShoppingList(user.userId()));
    }

    public List<MissingIngredientByMealResponseDTO> getAllIngredientMissingByMeal(AuthenticatedUser user, OffsetDateTime startDate, OffsetDateTime endDate) {
        return ingredientRepository.getAllIngredientMissingByMeal(user.userId(), startDate, endDate);
    }
}
