package io.sponges.bot.dashboard.dao;

import java.util.UUID;

public class ClientUserDAO extends DAO {

    private final UUID id;
    private final UUID user;
    private final UUID botId;
    private final UUID client;

    public ClientUserDAO(UUID id, UUID user, UUID botId, UUID client) {
        this.id = id;
        this.user = user;
        this.botId = botId;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUser() {
        return user;
    }

    public UUID getBotId() {
        return botId;
    }

    public UUID getClient() {
        return client;
    }
}
