package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SelectUserCredentialsStatement extends AbstractStatement<UserCredentialsDAO> {

    private final UUID user;

    public SelectUserCredentialsStatement(Database database, UUID user) {
        super(database, Statements.SELECT_USER_CREDENTIALS);
        this.user = user;
    }

    @Override
    protected UserCredentialsDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, user);
            ResultSet results = statement.executeQuery();
            if (!results.next()) {
                return null;
            }
            String email = results.getString(1);
            String password = results.getString(2);
            byte[] salt = results.getBytes(3);
            UserCredentialsDAO dao = new UserCredentialsDAO(user, email, password, salt);
            dao.setDatabase(database);
            return dao;
        }
    }
}
