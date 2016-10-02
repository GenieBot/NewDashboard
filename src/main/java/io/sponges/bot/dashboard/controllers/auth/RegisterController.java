package io.sponges.bot.dashboard.controllers.auth;

import io.sponges.bot.dashboard.Context;
import io.sponges.bot.dashboard.Controller;
import io.sponges.bot.dashboard.Method;
import io.sponges.bot.dashboard.Route;
import spark.ModelAndView;
import spark.Request;

public class RegisterController extends Controller {

    public RegisterController() {
        super("/register", false);
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("auth/register.ftl");
    }

    @Route(method = Method.POST)
    public String post(Context context) {
        Request request = context.getRequest();
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        String confirmPassword = request.queryParams("confirm_password");
        if (!validateStrings(email, password, confirmPassword)) {
            context.redirect("/register");
            context.alert("Invalid username, password or confirm password fields!");
            return null;
        }

        return "OK";
    }
}
