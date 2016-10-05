package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectClientIdStatement extends AbstractStatement<UUID> {

    private final UUID botId;

    public SelectClientIdStatement(Database database, UUID botId) {
        super(database, Statements.SELECT_CLIENT_ID);
        this.botId = botId;
    }

    @Override
    protected UUID execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, botId);
            ResultSet results = statement.executeQuery();
            if (!results.next()) {
                return null;
            }
            return UUID.fromString(results.getString(1));
        }
    }
}
