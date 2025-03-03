package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import com.fs.webpr.foodplanner_backend.entity.model.PantryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PantryMapper {

    Pantry toPantry(PantryDTO pantryDTO);
}
