package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
import com.fs.webpr.foodplanner_backend.entity.dao.KitchenDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KitchenMapper {

    KitchenDAO toDAO(Kitchen kitchen);
}
