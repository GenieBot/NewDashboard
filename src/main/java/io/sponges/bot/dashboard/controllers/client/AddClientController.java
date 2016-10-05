package io.sponges.bot.dashboard.controllers.client;

import io.sponges.bot.dashboard.*;
import io.sponges.bot.dashboard.entities.Client;
import io.sponges.bot.dashboard.entities.ClientManager;
import spark.ModelAndView;

import java.util.UUID;

public class AddClientController extends Controller {

    private final Configuration configuration;
    private final ClientManager clientManager;
    private final String discordAuthoriationUrl;

    public AddClientController(Configuration configuration, ClientManager clientManager) {
        super("/clients/add/:client", true);
        this.configuration = configuration;
        this.clientManager = clientManager;
        this.discordAuthoriationUrl = configuration.getJSONObject("clients").getJSONObject("discord")
                .getString("authorization_url");
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        UUID uuid = UUID.fromString(context.getRequest().params("client"));
        Client client = clientManager.getClient(uuid);
        if (client == null) {
            context.alert("Invalid client.");
            return context.createView("error.ftl");
        }
        switch (client.getName()) {
            case "discord": {
                context.redirect(discordAuthoriationUrl);
                break;
            }
            default:
                context.alert("Unsupported client.");
                return context.createView("error.ftl");
        }
        return null;
    }
}
