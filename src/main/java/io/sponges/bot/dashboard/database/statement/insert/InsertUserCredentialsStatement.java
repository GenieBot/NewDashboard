package io.sponges.bot.dashboard.database.statement.insert;

import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class InsertUserCredentialsStatement extends AbstractStatement<UserCredentialsDAO> {

    private final UUID user;
    private final String email;
    private final String password;
    private final byte[] salt;

    public InsertUserCredentialsStatement(Database database, UUID user, String email, String password, byte[] salt) {
        super(database, Statements.INSERT_USER_CREDENTIALS);
        this.user = user;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    @Override
    protected UserCredentialsDAO execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, user);
            statement.setObject(2, email);
            statement.setObject(3, password);
            statement.setBytes(4, salt);
            int result = statement.executeUpdate();
            if (result <= 0) return null;
            UserCredentialsDAO dao = new UserCredentialsDAO(user, email, password, salt);
            dao.setDatabase(database);
            return dao;
        }
    }
}
