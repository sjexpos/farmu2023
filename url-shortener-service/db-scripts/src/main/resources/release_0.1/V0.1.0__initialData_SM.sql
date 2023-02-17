CREATE TABLE "url" (
    "id"          SERIAL PRIMARY KEY,
    "key"         varchar   NOT NULL,
    "url"         varchar,
    "created_at"  timestamp NOT NULL DEFAULT (now()),
    "created_by"  varchar,
    "modified_at" timestamp,
    "modified_by" varchar,
    "deleted_at"  timestamp,
    "deleted_by"  varchar
);

GRANT ALL PRIVILEGES ON DATABASE ${flyway:database} TO url_shortener_service;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO url_shortener_service;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO url_shortener_service;

