package io.sponges.bot.dashboard.controllers.network;

import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Route;
import spark.ModelAndView;

public class NetworksController extends Controller {

    public NetworksController() {
        super("/networks", true);
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("network/view_networks.ftl");
    }
}
