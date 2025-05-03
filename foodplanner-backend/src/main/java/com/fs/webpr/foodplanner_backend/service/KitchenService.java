package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.response.KitchenResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.mapper.KitchenMapper;
import com.fs.webpr.foodplanner_backend.repository.KitchenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final KitchenMapper kitchenMapper;

    public List<KitchenResponseDTO> getAll() {
        return kitchenMapper.toKitchenResponseDTO(kitchenRepository.findAll());
    }
}
