package io.sponges.bot.dashboard.database.statement.update;

import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class UpdateOAuthStatement extends AbstractStatement<Integer> {

    private final String token;
    private final String refreshToken;
    private final int expiresIn;
    private final UUID user;

    public UpdateOAuthStatement(Database database, String token, String refreshToken, int expiresIn, UUID user) {
        super(database, Statements.UPDATE_OAUTH);
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.user = user;
    }

    @Override
    protected Integer execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setString(1, token);
            statement.setString(2, refreshToken);
            statement.setInt(3, expiresIn);
            statement.setObject(4, user);
            return statement.executeUpdate();
        }
    }
}
