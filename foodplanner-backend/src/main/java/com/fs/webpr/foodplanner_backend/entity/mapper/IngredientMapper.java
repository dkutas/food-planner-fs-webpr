package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.IngredientDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientDAO toDAO(Ingredient ingredient);
}
