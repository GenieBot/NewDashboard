package io.sponges.bot.dashboard.database.statement.insert;

import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;
import io.sponges.bot.dashboard.dao.NetworkDAO;
import io.sponges.bot.dashboard.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class InsertNetworkStatement extends AbstractStatement<NetworkDAO> {

    private final UUID uuid;
    private final UUID botId;
    private final UUID client;

    public InsertNetworkStatement(Database database, UUID uuid, UUID botId, UUID client) {
        super(database, Statements.INSERT_NETWORK);
        this.uuid = uuid;
        this.botId = botId;
        this.client = client;
    }

    @Override
    protected NetworkDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, uuid);
            statement.setObject(2, botId);
            statement.setObject(3, client);
            int result = statement.executeUpdate();
            if (result <= 0) return null;
            NetworkDAO dao = new NetworkDAO(uuid, botId, client);
            dao.setDatabase(database);
            return dao;
        }
    }
}
