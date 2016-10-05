package io.sponges.bot.dashboard.dao;

import io.sponges.bot.dashboard.database.statement.update.UpdateUserCredentialsStatement;

import java.util.UUID;

public class UserCredentialsDAO extends DAO {

    private final UUID user;
    private String email;
    private String password;
    private byte[] salt;

    public UserCredentialsDAO(UUID user, String email, String password, byte[] salt) {
        this.user = user;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public UUID getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void update() throws Exception {
        new UpdateUserCredentialsStatement(database, email, password, salt, user).executeAsync();
    }

}
