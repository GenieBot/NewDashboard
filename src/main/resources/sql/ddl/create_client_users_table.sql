CREATE TABLE IF NOT EXISTS client_users (
  id UUID PRIMARY KEY NOT NULL,
  user_id UUID NOT NULL,
  bot_id UUID NOT NULL,
  client UUID NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (client) REFERENCES clients(id)
);