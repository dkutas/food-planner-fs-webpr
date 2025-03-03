package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.mapper.IngredientCategoryMapper;
import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategory;
import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategoryDAO;
import com.fs.webpr.foodplanner_backend.repository.IngredientCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientCategoryService {

    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final IngredientCategoryMapper ingredientCategoryMapper;

    public List<IngredientCategoryDAO> getAll() {
        List<IngredientCategory> ingredientCategories = ingredientCategoryRepository.findAll();

        return ingredientCategories.stream().map(ingredientCategoryMapper::toDAO).toList();
    }
}
