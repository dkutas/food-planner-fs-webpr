package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.repository.ShoppingListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
}
