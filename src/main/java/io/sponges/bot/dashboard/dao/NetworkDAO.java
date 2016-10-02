package io.sponges.bot.dashboard.dao;

import java.util.UUID;

public class NetworkDAO extends DAO {

    private final UUID id;
    private final UUID botId;
    private final UUID clientId;

    public NetworkDAO(UUID id, UUID botId, UUID clientId) {
        this.id = id;
        this.botId = botId;
        this.clientId = clientId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBotId() {
        return botId;
    }

    public UUID getClientId() {
        return clientId;
    }
}
