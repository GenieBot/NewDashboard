package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.database.statement.AbstractStatement;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectClientUserIdStatement extends AbstractStatement<UUID> {

    private final UUID client;
    private final UUID user;

    public SelectClientUserIdStatement(Database database, UUID client, UUID user) {
        super(database, Statements.SELECT_CLIENT_USER_ID);
        this.client = client;
        this.user = user;
    }

    @Override
    protected UUID execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, client);
            statement.setObject(2, user);
            ResultSet results = statement.executeQuery();
            if (!results.isBeforeFirst()) {
                return null;
            }
            return UUID.fromString(results.getString(1));
        }
    }
}
