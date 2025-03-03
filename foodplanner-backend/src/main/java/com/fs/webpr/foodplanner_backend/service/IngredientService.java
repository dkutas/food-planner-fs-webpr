package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.mapper.IngredientMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.IngredientDAO;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public List<IngredientDAO> getAll() {
        List<Ingredient> ingredients = ingredientRepository.findAll();

        return ingredients.stream().map(ingredientMapper::mapToDAO).toList();
    }
}
