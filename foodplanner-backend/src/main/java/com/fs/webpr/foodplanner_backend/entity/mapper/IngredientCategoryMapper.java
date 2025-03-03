package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategory;
import com.fs.webpr.foodplanner_backend.entity.dao.IngredientCategoryDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientCategoryMapper {

    IngredientCategoryDAO toDAO(IngredientCategory ingredientCategory);
}
