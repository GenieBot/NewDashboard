package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.dao.NetworkDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectNetworkStatement extends AbstractStatement<NetworkDAO> {

    private final UUID id;

    public SelectNetworkStatement(Database database, UUID id) {
        super(database, Statements.SELECT_NETWORK);
        this.id = id;
    }

    @Override
    protected NetworkDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, id);
            ResultSet results = statement.executeQuery();
            if (!results.isBeforeFirst()) {
                return null;
            }
            UUID botId = UUID.fromString(results.getString(1));
            UUID client = UUID.fromString(results.getString(2));
            NetworkDAO dao = new NetworkDAO(id, botId, client);
            dao.setDatabase(database);
            return dao;
        }
    }
}
