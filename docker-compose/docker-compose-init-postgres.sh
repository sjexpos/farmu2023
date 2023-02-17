#!/usr/bin/env bash

sed -r "s#TO_BE_REPLACED#1234#g" /mnt/create_databases.sql > /tmp/create_databases.sql

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -f /tmp/create_databases.sql

echo "------------ Databases were created ------------"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

-- GRANT ALL PRIVILEGES ON DATABASE farmu_shortener TO url_shortener_service;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO url_shortener_service;

EOSQL

echo "------------ Grants were executed ------------"

