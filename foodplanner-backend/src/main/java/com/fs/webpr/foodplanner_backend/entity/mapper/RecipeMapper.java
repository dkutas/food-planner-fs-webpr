package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dto.request.RecipeRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.RecipeResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper {

    Recipe toRecipe(RecipeRequestDTO recipeRequestDTO);

    RecipeResponseDTO toRecipeResponseDTO(Recipe recipe);

    List<RecipeResponseDTO> toRecipeResponseDTO(List<Recipe> recipes);
}
