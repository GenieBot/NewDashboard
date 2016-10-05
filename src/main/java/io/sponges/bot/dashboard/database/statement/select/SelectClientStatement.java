package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.dao.ClientDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectClientStatement extends AbstractStatement<ClientDAO> {

    private final UUID id;

    public SelectClientStatement(Database database, UUID id) {
        super(database, Statements.SELECT_CLIENT);
        this.id = id;
    }

    @Override
    protected ClientDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, id);
            ResultSet results = statement.executeQuery();
            if (!results.next()) {
                return null;
            }
            UUID botId = UUID.fromString(results.getString(1));
            int authMethod = results.getInt(2);
            String clientName = results.getString(3);
            ClientDAO dao = new ClientDAO(id, botId, authMethod, clientName);
            dao.setDatabase(database);
            return dao;
        }
    }
}
