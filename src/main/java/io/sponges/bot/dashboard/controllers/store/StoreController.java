package io.sponges.bot.dashboard.controllers.store;

import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Route;
import spark.ModelAndView;

public class StoreController extends Controller {

    public StoreController() {
        super("/store", true);
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("store/store.ftl");
    }
}
