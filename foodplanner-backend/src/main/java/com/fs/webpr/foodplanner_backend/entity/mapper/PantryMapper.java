package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dto.request.PantryRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.PantryResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PantryMapper {

    Pantry toPantry(PantryRequestDTO pantryRequestDTO);

    PantryResponseDTO toPantryResponseDTO(Pantry pantry);

    List<PantryResponseDTO> toPantryResponseDTO(List<Pantry> pantries);
}
