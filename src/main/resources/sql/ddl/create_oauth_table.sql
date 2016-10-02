CREATE TABLE IF NOT EXISTS oauth (
  user_id UUID NOT NULL,
  token VARCHAR(255),
  refresh_token VARCHAR(255),
  expires_in INT,
  FOREIGN KEY (user_id) REFERENCES client_users(id)
);