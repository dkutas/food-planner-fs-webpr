CREATE TABLE ingredient
(
    id                     UUID         NOT NULL,
    name                   VARCHAR(255) NOT NULL,
    ingredient_category_id UUID         NOT NULL,
    CONSTRAINT pk_ingredient PRIMARY KEY (id)
);

CREATE TABLE ingredient_category
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_ingredientcategory PRIMARY KEY (id)
);

CREATE TABLE kitchen
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_kitchen PRIMARY KEY (id)
);

CREATE TABLE meal_plan
(
    id         UUID                     NOT NULL,
    recipe_id  UUID                     NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date   TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT pk_mealplan PRIMARY KEY (id)
);

CREATE TABLE pantry
(
    id            UUID NOT NULL,
    ingredient_id UUID NOT NULL,
    CONSTRAINT pk_pantry PRIMARY KEY (id)
);

CREATE TABLE recipe
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    kitchen_id  UUID         NOT NULL,
    CONSTRAINT pk_recipe PRIMARY KEY (id)
);

CREATE TABLE recipe_ingredient
(
    ingredient_id UUID NOT NULL,
    recipe_id     UUID NOT NULL,
    CONSTRAINT pk_recipe_ingredient PRIMARY KEY (ingredient_id, recipe_id)
);

CREATE TABLE shopping_list
(
    id            UUID NOT NULL,
    ingredient_id UUID NOT NULL,
    CONSTRAINT pk_shoppinglist PRIMARY KEY (id)
);

ALTER TABLE pantry
    ADD CONSTRAINT uc_pantry_ingredient UNIQUE (ingredient_id);

ALTER TABLE shopping_list
    ADD CONSTRAINT uc_shoppinglist_ingredient UNIQUE (ingredient_id);

ALTER TABLE ingredient
    ADD CONSTRAINT fk_ingredient_on_ingredient_category FOREIGN KEY (ingredient_category_id) REFERENCES ingredient_category (id);

ALTER TABLE meal_plan
    ADD CONSTRAINT fk_meal_plan_on_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE pantry
    ADD CONSTRAINT fk_pantry_on_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (id);

ALTER TABLE recipe
    ADD CONSTRAINT fk_recipe_on_kitchen FOREIGN KEY (kitchen_id) REFERENCES kitchen (id);

ALTER TABLE shopping_list
    ADD CONSTRAINT fk_shopping_list_on_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (id);

ALTER TABLE recipe_ingredient
    ADD CONSTRAINT fk_recipe_ingredient_on_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (id);

ALTER TABLE recipe_ingredient
    ADD CONSTRAINT fk_recipe_ingredient_on_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (id);