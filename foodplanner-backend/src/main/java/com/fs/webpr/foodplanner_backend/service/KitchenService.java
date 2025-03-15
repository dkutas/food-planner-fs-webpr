package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
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

    public List<Kitchen> getAll() {
        return kitchenRepository.findAll();
    }
}
