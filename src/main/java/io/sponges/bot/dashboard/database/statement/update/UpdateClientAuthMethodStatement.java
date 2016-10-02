package io.sponges.bot.dashboard.database.statement.update;

import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class UpdateClientAuthMethodStatement extends AbstractStatement<Integer> {

    private final int authMethod;
    private final UUID client;

    public UpdateClientAuthMethodStatement(Database database, int authMethod, UUID client) {
        super(database, Statements.UPDATE_CLIENT_AUTH_METHOD);
        this.authMethod = authMethod;
        this.client = client;
    }

    @Override
    protected Integer execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setInt(1, authMethod);
            statement.setObject(2, client);
            return statement.executeUpdate();
        }
    }
}
