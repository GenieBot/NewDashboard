package io.sponges.bot.dashboard.controllers.client;

import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Route;
import spark.ModelAndView;

public class PickClientController extends Controller {

    public PickClientController() {
        super("/clients/add", true);
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("client/pick_client.ftl");
    }
}
