package io.sponges.bot.dashboard.dao;

import io.sponges.bot.dashboard.database.statement.update.UpdateClientStatement;

import java.util.UUID;

public class ClientDAO extends DAO {

    private final UUID id;
    private final UUID botId;
    private int authMethod;
    private String clientName;

    public ClientDAO(UUID id, UUID botId, int authMethod, String clientName) {
        this.id = id;
        this.botId = botId;
        this.authMethod = authMethod;
        this.clientName = clientName;
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
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void update() throws Exception {
        new UpdateClientStatement(database, authMethod, clientName, id).executeAsync();
    }
}
