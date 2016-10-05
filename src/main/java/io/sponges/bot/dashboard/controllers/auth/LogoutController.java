package io.sponges.bot.dashboard.controllers.auth;

import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Route;
import io.sponges.bot.dashboard.entities.UserManager;

public class LogoutController extends Controller {

    private final UserManager userManager;

    public LogoutController(UserManager userManager) {
        super("/logout", true);
        this.userManager = userManager;
    }

    @Route(method = Method.GET)
    public void logout(Context context) {
        userManager.logoutUser(context.getSession());
        context.alert("Logged out!");
        context.redirect("/");
    }
}
