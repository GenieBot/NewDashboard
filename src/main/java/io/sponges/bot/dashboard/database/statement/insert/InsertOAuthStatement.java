package io.sponges.bot.dashboard.database.statement.insert;

import io.sponges.bot.dashboard.dao.OAuthDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class InsertOAuthStatement extends AbstractStatement<OAuthDAO> {

    private final UUID user;
    private final String token;
    private final String refreshToken;
    private final int expiresIn;
    private final int startTime;

    public InsertOAuthStatement(Database database, UUID user, String token, String refreshToken, int expiresIn, int startTime) {
        super(database, Statements.INSERT_OAUTH);
        this.user = user;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.startTime = startTime;
    }

    @Override
    protected OAuthDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, user);
            statement.setString(2, token);
            statement.setString(3, refreshToken);
            statement.setInt(4, expiresIn);
            statement.setInt(5, startTime);
            int result = statement.executeUpdate();
            if (result <= 0) return null;
            OAuthDAO dao = new OAuthDAO(user, token, refreshToken, expiresIn, startTime);
            dao.setDatabase(database);
            return dao;
        }
    }
}
