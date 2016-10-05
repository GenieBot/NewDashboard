package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.dao.OAuthDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectOAuthStatement extends AbstractStatement<OAuthDAO> {

    private final UUID user;

    public SelectOAuthStatement(Database database, UUID user) {
        super(database, Statements.SELECT_OAUTH);
        this.user = user;
    }

    @Override
    protected OAuthDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, user);
            ResultSet results = statement.executeQuery();
            if (!results.next()) {
                return null;
            }
            String token = results.getString(1);
            String refreshToken = results.getString(2);
            int expiresIn = results.getInt(3);
            int startTime = results.getInt(4);
            OAuthDAO dao = new OAuthDAO(user, token, refreshToken, expiresIn, startTime);
            dao.setDatabase(database);
            return dao;
        }
    }
}
