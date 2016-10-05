package io.sponges.bot.dashboard.controllers.client;

import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Route;
import io.sponges.bot.dashboard.entities.Client;
import io.sponges.bot.dashboard.entities.ClientManager;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PickClientController extends Controller {

    private final ClientManager clientManager;

    public PickClientController(ClientManager clientManager) {
        super("/clients/add", true);
        this.clientManager = clientManager;
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        Collection<Client> clients = clientManager.loadAllClients();
        List<Map<String, Object>> clientsList = new ArrayList<>();
        clients.forEach(client -> clientsList.add(client.toMap()));
        context.getModel().withMapping("clients", clientsList);
        return context.createView("client/pick_client.ftl");
    }
}
