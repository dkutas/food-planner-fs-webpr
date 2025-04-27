#!/bin/bash
set -e

# Create the "keycloak" schema
echo "Creating schema keycloak for food_planner_db with user admin"

psql -v ON_ERROR_STOP=1 --username admin --dbname food_planner_db <<-EOSQL
  CREATE SCHEMA IF NOT EXISTS keycloak;
EOSQL

echo "Schema 'keycloak' created successfully!"
