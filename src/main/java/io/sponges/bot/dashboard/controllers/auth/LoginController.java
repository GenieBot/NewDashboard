package io.sponges.bot.dashboard.controllers.auth;

import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Route;
import spark.ModelAndView;

public class LoginController extends Controller {

    public LoginController() {
        super("/login", false);
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("auth/login.ftl");
    }

    @Route(method = Method.POST)
    public String post(Context context) {
        return "ok lol havent impld yet xdxd";
    }
}
