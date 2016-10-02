package io.sponges.bot.dashboard.dao;

import java.util.UUID;

public class UserDAO extends DAO {

    private final UUID id;

    public UserDAO(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
