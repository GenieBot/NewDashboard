CREATE TABLE IF NOT EXISTS user_credentials (
  id UUID not null,
  email VARCHAR(255) not null,
  password CHAR(128) not null,
  salt CHAR(128) not null,
  FOREIGN KEY (id) REFERENCES users(id)
);