package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import com.fs.webpr.foodplanner_backend.entity.dto.PantryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PantryMapper {

    Pantry toPantry(PantryDTO pantryDTO);
}
