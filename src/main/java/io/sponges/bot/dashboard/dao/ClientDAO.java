package io.sponges.bot.dashboard.dao;

import io.sponges.bot.dashboard.database.statement.update.UpdateClientAuthMethodStatement;

import java.util.UUID;

public class ClientDAO extends DAO {

    private final UUID id;
    private final UUID botId;
    private int authMethod;

    public ClientDAO(UUID id, UUID botId, int authMethod) {
        this.id = id;
        this.botId = botId;
        this.authMethod = authMethod;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBotId() {
        return botId;
    }

    public int getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(int authMethod) throws Exception {
        this.authMethod = authMethod;
        new UpdateClientAuthMethodStatement(database, authMethod, id).executeAsync();
    }
}
