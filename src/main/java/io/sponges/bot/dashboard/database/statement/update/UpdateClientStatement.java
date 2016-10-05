package io.sponges.bot.dashboard.database.statement.update;

import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class UpdateClientStatement extends AbstractStatement<Integer> {

    private final int authMethod;
    private final String clientName;
    private final UUID client;

    public UpdateClientStatement(Database database, int authMethod, String clientName, UUID client) {
        super(database, Statements.UPDATE_CLIENT);
        this.authMethod = authMethod;
        this.clientName = clientName;
        this.client = client;
    }

    @Override
    protected Integer execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setInt(1, authMethod);
            statement.setString(2, clientName);
            statement.setObject(3, client);
            return statement.executeUpdate();
        }
    }
}
