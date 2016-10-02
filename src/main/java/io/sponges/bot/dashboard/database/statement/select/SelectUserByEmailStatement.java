package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectUserByEmailStatement extends AbstractStatement<UUID> {

    private final String email;

    public SelectUserByEmailStatement(Database database, String email) {
        super(database, Statements.SELECT_USER_BY_EMAIL);
        this.email = email;
    }

    @Override
    protected UUID execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();
            if (!results.isBeforeFirst()) {
                return null;
            }
            return UUID.fromString(results.getString(1));
        }
    }
}
