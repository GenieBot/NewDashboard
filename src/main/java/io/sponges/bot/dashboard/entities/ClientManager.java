package io.sponges.bot.dashboard.entities;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.sponges.bot.dashboard.API;
import io.sponges.bot.dashboard.dao.ClientDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.statement.insert.InsertClientStatement;
import io.sponges.bot.dashboard.database.statement.select.SelectClientIdStatement;
import io.sponges.bot.dashboard.database.statement.select.SelectClientStatement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientManager {

    private final Map<UUID, Client> clients = new HashMap<>();

    private final Database database;

    public ClientManager(Database database) {
        this.database = database;
        loadAllClients();
    }

    public Client getClient(UUID uuid) {
        return clients.get(uuid);
    }

    public Client getClientByName(String name) {
        for (Client client : clients.values()) {
            if (client.getName().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return null;
    }

    private Client insertClient(UUID botId, String clientName) throws Exception {
        UUID uuid = UUID.randomUUID();
        // TODO actual method to find authMethod
        int authMethod = 0;
        ClientDAO dao = new InsertClientStatement(database, uuid, botId, authMethod, clientName).executeAsync().get();
        Client client = new Client(dao);
        clients.put(uuid, client);
        return client;
    }

    private Client loadClient(UUID uuid) throws Exception {
        if (clients.containsKey(uuid)) {
            return clients.get(uuid);
        }
        ClientDAO dao = new SelectClientStatement(database, uuid).executeAsync().get();
        Client client = new Client(dao);
        clients.put(uuid, client);
        return client;
    }

    public Client loadOrInsertClientByBotId(UUID botId, String clientName) throws Exception {
        for (Client client : clients.values()) {
            if (client.getBotId().equals(botId)) {
                return client;
            }
        }
        UUID clientId = new SelectClientIdStatement(database, botId).executeAsync().get();
        if (clientId == null) {
            return insertClient(botId, clientName);
        }
        return loadClient(clientId);
    }

    private JSONObject getAPIClient(UUID id) {
        JSONObject json;
        try {
            json = API.CLIENT.get(id.toString()).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
        if (json.getBoolean("error")) {
            return null;
        }
        return json.getJSONObject("content");
    }

    public Collection<Client> loadAllClients() {
        JSONObject json;
        try {
            json = API.CLIENTS.get().asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
        if (json.getBoolean("error")) {
            return null;
        }
        JSONObject content = json.getJSONObject("content");
        JSONArray array = content.getJSONArray("clients");
        for (int i = 0; i < array.length(); i++) {
            String entry = array.getString(i);
            UUID botId = UUID.fromString(entry);
            if (clients.containsKey(botId)) continue;
            JSONObject client = getAPIClient(botId);
            if (client == null) continue;
            try {
                loadOrInsertClientByBotId(botId, client.getString("source_id"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clients.values();
    }

}
