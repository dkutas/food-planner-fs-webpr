package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.repository.KitchenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;
}
