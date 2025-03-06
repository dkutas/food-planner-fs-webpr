package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.dao.IngredientDAO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientMapper {

    IngredientDAO toDAO(Ingredient ingredient);
}
