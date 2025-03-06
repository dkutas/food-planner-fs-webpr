package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategory;
import com.fs.webpr.foodplanner_backend.entity.dao.IngredientCategoryDAO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientCategoryMapper {

    IngredientCategoryDAO toDAO(IngredientCategory ingredientCategory);
}
