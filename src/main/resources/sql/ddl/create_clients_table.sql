CREATE TABLE IF NOT EXISTS clients (
  id UUID PRIMARY KEY NOT NULL,
  bot_id UUID NOT NULL,
  auth_method INT NOT NULL
);