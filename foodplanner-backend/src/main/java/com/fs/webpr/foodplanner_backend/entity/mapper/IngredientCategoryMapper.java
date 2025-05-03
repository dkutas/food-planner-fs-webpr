package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dto.response.IngredientCategoryResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientCategoryMapper {

    List<IngredientCategoryResponseDTO> toIngredientCategoryResponseDTO(List<IngredientCategory> ingredientCategories);

}
