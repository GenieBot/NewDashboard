package io.sponges.bot.dashboard.entities;

import io.sponges.bot.dashboard.dao.ClientUserDAO;
import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.dao.UserDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.statement.insert.InsertUserCredentialsStatement;
import io.sponges.bot.dashboard.database.statement.insert.InsertUserStatement;
import io.sponges.bot.dashboard.database.statement.select.SelectClientUserIdsStatement;
import io.sponges.bot.dashboard.database.statement.select.SelectClientUserStatement;
import io.sponges.bot.dashboard.database.statement.select.SelectUserByEmailStatement;
import io.sponges.bot.dashboard.database.statement.select.SelectUserCredentialsStatement;
import spark.Session;

import java.util.*;

public class UserManager {

    private final Map<String, User> users = new HashMap<>();

    private final Database database;

    public UserManager(Database database) {
        this.database = database;
    }

    public User getUser(Session session) {
        return users.get(session.id());
    }

    public User loadUser(Session session, UUID userId) throws Exception {
        for (User user : users.values()) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        UserDAO userDAO = new UserDAO(userId);
        UserCredentialsDAO credentialsDAO = new SelectUserCredentialsStatement(database, userId).executeAsync().get();
        if (credentialsDAO == null) {
            return null;
        }
        List<ClientUserDAO> clientUserDAOs = new ArrayList<>();
        Set<UUID> clientUserIds = new SelectClientUserIdsStatement(database, userId).executeAsync().get();
        if (clientUserIds != null) {
            clientUserIds.forEach(id -> {
                try {
                    ClientUserDAO dao = new SelectClientUserStatement(database, id).executeAsync().get();
                    clientUserDAOs.add(dao);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        User user = new User(userDAO, credentialsDAO, clientUserDAOs, session);
        users.put(session.id(), user);
        return user;
    }

    public User loadUser(Session session, String email) throws Exception {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        UUID userId = new SelectUserByEmailStatement(database, email).executeAsync().get();
        if (userId == null) {
            return null;
        }
        return loadUser(session, userId);
    }

    public User createUser(Session session, String email, String password, byte[] salt) throws Exception {
        UUID uuid = UUID.randomUUID();
        new InsertUserStatement(database, uuid).executeAsync();
        new InsertUserCredentialsStatement(database, uuid, email, password, salt).executeAsync();
        return loadUser(session, uuid);
    }

    public boolean isLoggedIn(Session session) {
        return users.containsKey(session.id());
    }

    public void loginUser(Session session, User user) {
        users.put(session.id(), user);
    }

    public void logoutUser(Session session) {
        users.remove(session.id());
    }

}
