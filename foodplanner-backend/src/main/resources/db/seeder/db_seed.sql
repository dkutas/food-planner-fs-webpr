-- Delete from pantry
DELETE FROM pantry;

-- Delete from shopping_list
DELETE FROM shopping_list;

-- Delete from recipe_ingredient
DELETE FROM recipe_ingredient;

-- Delete from meal_plan
DELETE FROM meal_plan;

-- Delete from ingredient
DELETE FROM ingredient;

-- Delete from recipe
DELETE FROM recipe;

-- Delete from kitchen
DELETE FROM kitchen;

-- Delete from ingredient_category
DELETE FROM ingredient_category;

-- Insert into ingredient_category
INSERT INTO ingredient_category (id, name)
VALUES (gen_random_uuid(), 'Vegetables'),
       (gen_random_uuid(), 'Meats'),
       (gen_random_uuid(), 'Spices'),
       (gen_random_uuid(), 'Dairy');

-- Insert into ingredient
INSERT INTO ingredient (id, name, ingredient_category_id)
VALUES (gen_random_uuid(), 'Carrot', (SELECT id FROM ingredient_category WHERE name = 'Vegetables')),
       (gen_random_uuid(), 'Chicken', (SELECT id FROM ingredient_category WHERE name = 'Meats')),
       (gen_random_uuid(), 'Salt', (SELECT id FROM ingredient_category WHERE name = 'Spices')),
       (gen_random_uuid(), 'Cheese', (SELECT id FROM ingredient_category WHERE name = 'Dairy'));

-- Insert into kitchen
INSERT INTO kitchen (id, name)
VALUES (gen_random_uuid(), 'Main Kitchen'),
       (gen_random_uuid(), 'Side Kitchen');

-- Insert into recipe
INSERT INTO recipe (id, name, description, kitchen_id)
VALUES (gen_random_uuid(), 'Grilled Chicken', 'A delicious grilled chicken recipe.',
        (SELECT id FROM kitchen WHERE name = 'Main Kitchen')),
       (gen_random_uuid(), 'Carrot Salad', 'A healthy carrot salad with light dressing.',
        (SELECT id FROM kitchen WHERE name = 'Side Kitchen'));

-- Insert into recipe_ingredient
INSERT INTO recipe_ingredient (ingredient_id, recipe_id)
VALUES ((SELECT id FROM ingredient WHERE name = 'Carrot'), (SELECT id FROM recipe WHERE name = 'Carrot Salad')),
       ((SELECT id FROM ingredient WHERE name = 'Chicken'), (SELECT id FROM recipe WHERE name = 'Grilled Chicken')),
       ((SELECT id FROM ingredient WHERE name = 'Salt'), (SELECT id FROM recipe WHERE name = 'Grilled Chicken')),
       ((SELECT id FROM ingredient WHERE name = 'Cheese'), (SELECT id FROM recipe WHERE name = 'Carrot Salad'));

-- Insert into meal_plan with timezone-aware dates
INSERT INTO meal_plan (id, recipe_id, start_date, end_date)
VALUES (gen_random_uuid(), (SELECT id FROM recipe WHERE name = 'Grilled Chicken'),
        '2025-03-18 00:00:00+00', '2025-03-18 23:59:59+00'), -- UTC timezone
       (gen_random_uuid(), (SELECT id FROM recipe WHERE name = 'Carrot Salad'),
        '2025-03-19 00:00:00+00', '2025-03-19 23:59:59+00');
-- UTC timezone

-- Insert into pantry
INSERT INTO pantry (id, ingredient_id)
VALUES (gen_random_uuid(), (SELECT id FROM ingredient WHERE name = 'Carrot')),
       (gen_random_uuid(), (SELECT id FROM ingredient WHERE name = 'Chicken'));

-- Insert into shopping_list
INSERT INTO shopping_list (id, ingredient_id)
VALUES (gen_random_uuid(), (SELECT id FROM ingredient WHERE name = 'Salt')),
       (gen_random_uuid(), (SELECT id FROM ingredient WHERE name = 'Cheese'));