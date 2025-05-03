package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dto.response.KitchenResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KitchenMapper {

    List<KitchenResponseDTO> toKitchenResponseDTO(List<Kitchen> kitchens);

}
