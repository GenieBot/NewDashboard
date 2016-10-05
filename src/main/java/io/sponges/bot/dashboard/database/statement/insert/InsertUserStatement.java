package io.sponges.bot.dashboard.database.statement.insert;

import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;
import io.sponges.bot.dashboard.dao.UserDAO;
import io.sponges.bot.dashboard.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class InsertUserStatement extends AbstractStatement<UserDAO> {

    private final UUID id;

    public InsertUserStatement(Database database, UUID id) {
        super(database, Statements.INSERT_USER);
        this.id = id;
    }

    @Override
    protected UserDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, id);
            int result = statement.executeUpdate();
            if (result <= 0) return null;
            UserDAO dao = new UserDAO(id);
            dao.setDatabase(database);
            return dao;
        }
    }
}
