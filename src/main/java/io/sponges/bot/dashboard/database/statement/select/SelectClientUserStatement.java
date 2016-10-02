package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.dao.ClientUserDAO;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectClientUserStatement extends AbstractStatement<ClientUserDAO> {

    private final UUID id;

    public SelectClientUserStatement(Database database, UUID id) {
        super(database, Statements.SELECT_CLIENT_USER);
        this.id = id;
    }

    @Override
    protected ClientUserDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, id);
            ResultSet results = statement.executeQuery();
            if (!results.isBeforeFirst()) {
                return null;
            }
            UUID user = UUID.fromString(results.getString(1));
            UUID botId = UUID.fromString(results.getString(2));
            UUID client = UUID.fromString(results.getString(3));
            ClientUserDAO dao = new ClientUserDAO(id, user, botId, client);
            dao.setDatabase(database);
            return dao;
        }
    }
}
