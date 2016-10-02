package io.sponges.bot.dashboard.database.statement.insert;

import io.sponges.bot.dashboard.dao.ClientUserDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class InsertClientUserStatement extends AbstractStatement<ClientUserDAO> {

    private final UUID id;
    private final UUID user;
    private final UUID botId;
    private final UUID client;

    public InsertClientUserStatement(Database database, UUID id, UUID user, UUID botId, UUID client) {
        super(database, Statements.INSERT_CLIENT_USER);
        this.id = id;
        this.user = user;
        this.botId = botId;
        this.client = client;
    }

    @Override
    protected ClientUserDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, id);
            statement.setObject(2, user);
            statement.setObject(3, botId);
            statement.setObject(4, client);
            if (statement.execute()) {
                ClientUserDAO dao = new ClientUserDAO(id, user, client, botId);
                dao.setDatabase(database);
                return dao;
            }
            return null;
        }
    }
}
