package io.sponges.bot.dashboard.dao;

import io.sponges.bot.dashboard.database.statement.update.UpdateOAuthStatement;

import java.util.UUID;

public class OAuthDAO extends DAO {

    private final UUID user;
    private String token;
    private String refreshToken;
    private int expiresIn;

    public OAuthDAO(UUID user, String token, String refreshToken, int expiresIn) {
        this.user = user;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public UUID getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void update() throws Exception {
        new UpdateOAuthStatement(database, token, refreshToken, expiresIn, user).executeAsync();
    }
}
