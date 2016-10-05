package io.sponges.bot.dashboard.database.statement.update;

import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;
import io.sponges.bot.dashboard.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class UpdateUserCredentialsStatement extends AbstractStatement<Integer> {

    private final String email;
    private final String password;
    private final byte[] salt;
    private final UUID user;

    public UpdateUserCredentialsStatement(Database database, String email, String password, byte[] salt, UUID user) {
        super(database, Statements.UPDATE_USER_CREDENTIALS);
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.user = user;
    }

    @Override
    protected Integer execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setBytes(3, salt);
            statement.setObject(4, user);
            return statement.executeUpdate();
        }
    }
}
