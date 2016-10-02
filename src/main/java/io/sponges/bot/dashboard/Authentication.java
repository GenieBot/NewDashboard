package io.sponges.bot.dashboard;

import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.statement.select.SelectUserCredentialsStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Authentication {

    private final Map<UUID, UserCredentialsDAO> credentials = new HashMap<>();

    private final Database database;

    public Authentication(Database database) {
        this.database = database;
    }

    public UserCredentialsDAO getCredentials(UUID user) {
        if (credentials.containsKey(user)) {
            return credentials.get(user);
        }
        UserCredentialsDAO dao;
        try {
            dao = new SelectUserCredentialsStatement(database, user).executeSync();
            credentials.put(user, dao);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dao;
    }

}
