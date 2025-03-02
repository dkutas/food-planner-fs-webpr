package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.repository.IngredientCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientCategoryService {

    private final IngredientCategoryRepository ingredientCategoryRepository;
}
