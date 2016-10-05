package io.sponges.bot.dashboard.controllers.oauth;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.sponges.bot.dashboard.*;
import io.sponges.bot.dashboard.dao.ClientUserDAO;
import io.sponges.bot.dashboard.dao.OAuthDAO;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.database.statement.insert.InsertOAuthStatement;
import io.sponges.bot.dashboard.entities.Client;
import io.sponges.bot.dashboard.entities.ClientManager;
import io.sponges.bot.dashboard.entities.ClientUserManager;
import io.sponges.bot.dashboard.entities.UserManager;
import org.json.JSONObject;
import spark.Request;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OAuthController extends Controller {

    private final Database database;
    private final Configuration configuration;
    private final ClientManager clientManager;
    private final UserManager userManager;
    private final ClientUserManager clientUserManager;

    private final String discordTokenUrl;
    private final String discordRedirectUrl;
    private final String clientId;
    private final String clientSecret;

    public OAuthController(Database database, Configuration configuration, ClientManager clientManager, UserManager userManager, ClientUserManager clientUserManager) {
        super("/oauth/:client", true);
        this.database = database;
        this.configuration = configuration;
        this.clientManager = clientManager;
        this.userManager = userManager;
        this.clientUserManager = clientUserManager;
        JSONObject clients = configuration.getJSONObject("clients");
        JSONObject discord = clients.getJSONObject("discord");
        this.discordTokenUrl = discord.getString("token_url");
        this.discordRedirectUrl = discord.getString("redirect_url");
        this.clientId = discord.getString("client_id");
        this.clientSecret = discord.getString("client_secret");
    }

    @Route(method = Method.GET)
    public String get(Context context) {
        Request request = context.getRequest();
        String clientName = request.params("client");
        Client client = clientManager.getClientByName(clientName);
        if (client == null) {
            return "Invalid client!";
        }
        String code = request.queryParams("code");
        String networkId;
        JSONObject response;
        switch (client.getName().toLowerCase()) {
            case "discord": {
                networkId = request.queryParams("guild_id");
                try {
                    response = Unirest.post(String.format(discordTokenUrl, clientId, clientSecret, code,
                            discordRedirectUrl)).asJson().getBody().getObject();
                } catch (UnirestException e) {
                    e.printStackTrace();
                    return "Something went wrong";
                }
                break;
            }
            default:
                return "Unsupported client";
        }
        System.out.println(response.toString());
        String token = response.getString("access_token");
        String refreshToken = response.getString("refresh_token");
        int expiresIn = response.getInt("expires_in");
        int startTime = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        ClientUserDAO clientUserDAO;
        OAuthDAO oAuthDAO = null;
        try {
            clientUserDAO = clientUserManager.loadOrInsertUser(UUID.randomUUID(), context.getUser(), client, token, networkId);
            oAuthDAO = new InsertOAuthStatement(database, clientUserDAO.getId(), token, refreshToken, expiresIn,
                    startTime).executeAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
