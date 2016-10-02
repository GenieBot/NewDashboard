package io.sponges.bot.dashboard.controllers;

import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Route;
import spark.ModelAndView;

public class IndexController extends Controller {

    public IndexController() {
        super("/", false);
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("index.ftl");
    }
}
