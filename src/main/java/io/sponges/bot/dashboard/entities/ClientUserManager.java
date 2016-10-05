package io.sponges.bot.dashboard.entities;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.sponges.bot.dashboard.API;
import io.sponges.bot.dashboard.Configuration;
import io.sponges.bot.dashboard.dao.ClientUserDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.statement.insert.InsertClientUserStatement;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientUserManager {

    private final Map<UUID, ClientUserDAO> clientUsers = new HashMap<>();

    private final Database database;
    private final String discordUserInfoUrl;

    public ClientUserManager(Configuration configuration, Database database) {
        this.database = database;
        this.discordUserInfoUrl = configuration.getJSONObject("clients").getJSONObject("discord")
                .getString("user_info_url");
    }

    private JSONObject getUserInfo(Client client, String token) throws UnirestException {
        switch (client.getName().toLowerCase()) {
            case "discord": {
                return Unirest.get(discordUserInfoUrl).header("Authorization", "Bearer " + token).asJson().getBody().getObject();
            }
        }
        return null;
    }

    public ClientUserDAO loadOrInsertUser(UUID id, User user, Client client, String token, String network) throws Exception {
        JSONObject userInfo = getUserInfo(client, token);
        JSONObject networkObject = API.NETWORK.get(client.getBotId().toString(), network)
                .queryString("source_id", true).asJson().getBody().getObject();
        // TODO get user bot id
        UUID botId;
        ClientUserDAO dao = new InsertClientUserStatement(database, id, user.getId(), botId, client.getId())
                .executeAsync().get();
        clientUsers.put(id, dao);
        return dao;
    }

}
