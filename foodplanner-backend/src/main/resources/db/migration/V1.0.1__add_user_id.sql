ALTER TABLE meal_plan
    ADD COLUMN user_id UUID NOT NULL;

ALTER TABLE pantry
    ADD COLUMN user_id UUID NOT NULL;

ALTER TABLE recipe
    ADD COLUMN user_id UUID NOT NULL;

ALTER TABLE shopping_list
    ADD COLUMN user_id UUID NOT NULL;