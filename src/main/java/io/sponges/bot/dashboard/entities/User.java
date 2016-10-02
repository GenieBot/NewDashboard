package io.sponges.bot.dashboard.entities;

import io.sponges.bot.dashboard.dao.ClientUserDAO;
import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.dao.UserDAO;
import spark.Session;

import java.util.List;
import java.util.UUID;

public class User {

    private static final String SESSION_TOKEN_KEY = "session_token";

    private final UserDAO userDAO;
    private final UserCredentialsDAO userCredentialsDAO;
    private final List<ClientUserDAO> clientUserDAOs;
    private final Session session;
    private final UUID id;

    public User(UserDAO userDAO, UserCredentialsDAO userCredentialsDAO, List<ClientUserDAO> clientUserDAOs,
                Session session) {
        this.userDAO = userDAO;
        this.userCredentialsDAO = userCredentialsDAO;
        this.clientUserDAOs = clientUserDAOs;
        this.session = session;
        this.id = userDAO.getId();
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return userCredentialsDAO.getEmail();
    }

    public Session getSession() {
        return session;
    }

    public String getSessionToken() {
        return session.attribute(SESSION_TOKEN_KEY);
    }
}
