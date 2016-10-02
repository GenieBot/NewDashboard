package io.sponges.bot.dashboard.database.statement.insert;

import io.sponges.bot.dashboard.database.statement.AbstractStatement;
import io.sponges.bot.dashboard.dao.ClientDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class InsertClientStatement extends AbstractStatement<ClientDAO> {

    private final UUID id;
    private final UUID botId;
    private final int authMethod;

    public InsertClientStatement(Database database, UUID id, UUID botId, int authMethod) {
        super(database, Statements.INSERT_CLIENT);
        this.id = id;
        this.botId = botId;
        this.authMethod = authMethod;
    }

    @Override
    protected ClientDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, id);
            statement.setObject(2, botId);
            statement.setInt(3, authMethod);
            boolean result = statement.execute();
            if (result) {
                ClientDAO dao = new ClientDAO(id, botId, authMethod);
                dao.setDatabase(database);
                return dao;
            }
            return null;
        }
    }
}
