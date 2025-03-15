package com.fs.webpr.foodplanner_backend.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ingredient_category_id", nullable = false)
    private IngredientCategory category;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Recipe> recipes;
}
