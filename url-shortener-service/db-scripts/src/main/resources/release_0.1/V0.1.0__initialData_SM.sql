CREATE TABLE "short_url" (
    "id"          SERIAL PRIMARY KEY,
    "key"         varchar NOT NULL,
    "destination" varchar,
    "created_at"  timestamp NOT NULL DEFAULT (now()),
    "created_by"  varchar,
    "modified_at" timestamp,
    "modified_by" varchar
);

CREATE UNIQUE INDEX short_url_key ON short_url (key);

GRANT ALL PRIVILEGES ON DATABASE ${flyway:database} TO url_shortener_service;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO url_shortener_service;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO url_shortener_service;

