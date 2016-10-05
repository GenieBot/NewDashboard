package io.sponges.bot.dashboard.database.statement.select;

import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.Statements;
import io.sponges.bot.dashboard.database.statement.AbstractStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SelectClientUserIdsStatement extends AbstractStatement<Set<UUID>> {

    private final UUID user;

    public SelectClientUserIdsStatement(Database database, UUID user) {
        super(database, Statements.SELECT_CLIENT_USER_IDS);
        this.user = user;
    }

    @Override
    protected Set<UUID> execute() throws Exception {
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql());
            statement.setObject(1, user);
            ResultSet results = statement.executeQuery();
            if (!results.next()) {
                return null;
            }
            Set<UUID> ids = new HashSet<>();
            while (results.next()) {
                UUID result = UUID.fromString(results.getString(1));
                ids.add(result);
            }
            return ids;
        }
    }
}
