package io.sponges.bot.dashboard.entities;

import io.sponges.bot.dashboard.dao.ClientUserDAO;
import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.dao.UserDAO;
import spark.Session;

import java.util.List;
import java.util.UUID;

public class User {

    private final UserDAO userDAO;
    private final UserCredentialsDAO userCredentialsDAO;
    private final List<ClientUserDAO> clientUserDAOs;
    private final Session session;
    private final UUID id;

    User(UserDAO userDAO, UserCredentialsDAO userCredentialsDAO, List<ClientUserDAO> clientUserDAOs,
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

    public String getPassword() {
        return userCredentialsDAO.getPassword();
    }

    public byte[] getSalt() {
        return userCredentialsDAO.getSalt();
    }

    public UserCredentialsDAO getUserCredentialsDAO() {
        return userCredentialsDAO;
    }

    public Session getSession() {
        return session;
    }
}
