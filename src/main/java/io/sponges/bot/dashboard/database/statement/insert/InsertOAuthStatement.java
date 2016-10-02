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

    public InsertOAuthStatement(Database database, UUID user, String token, String refreshToken, int expiresIn) {
        super(database, Statements.INSERT_OAUTH);
        this.user = user;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    @Override
    protected OAuthDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, user);
            statement.setString(2, token);
            statement.setString(3, refreshToken);
            statement.setInt(4, expiresIn);
            if (statement.execute()) {
                OAuthDAO dao = new OAuthDAO(user, token, refreshToken, expiresIn);
                dao.setDatabase(database);
                return dao;
            }
            return null;
        }
    }
}
