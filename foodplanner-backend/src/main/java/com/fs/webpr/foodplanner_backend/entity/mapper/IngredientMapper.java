package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dto.response.IngredientResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientMapper {

    List<IngredientResponseDTO> toIngredientResponseDTO(List<Ingredient> ingredients);

}
