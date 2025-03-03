package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dao.PantryDAO;
import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import com.fs.webpr.foodplanner_backend.entity.dto.PantryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PantryMapper {

    Pantry toPantry(PantryDTO pantryDTO);

    PantryDAO toDAO(Pantry pantry);
}
