package io.sponges.bot.dashboard.controllers.auth;

import io.sponges.bot.dashboard.*;
import io.sponges.bot.dashboard.entities.User;
import io.sponges.bot.dashboard.entities.UserManager;
import spark.ModelAndView;
import spark.Request;
import spark.Session;

public class LoginController extends Controller {

    private final UserManager userManager;

    public LoginController(UserManager userManager) {
        super("/login", false);
        this.userManager = userManager;
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("auth/login.ftl");
    }

    @Route(method = Method.POST)
    public void post(Context context) {
        if (context.isLoggedIn()) {
            context.redirect("/");
            context.alert("You are already logged in!");
            return;
        }
        Request request = context.getRequest();
        Session session = request.session();
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        if (!valid(email, password)) {
            context.redirect("/login");
            context.alert("Invalid email or password fields!");
            return;
        }
        User user;
        try {
            user = userManager.loadUser(session, email);
        } catch (Exception e) {
            e.printStackTrace();
            context.redirect("/login");
            context.alert("Something went wrong!");
            return;
        }
        if (user == null) {
            context.redirect("/login");
            context.alert("Invalid email or password!");
            return;
        }
        byte[] salt = user.getSalt();
        password = Authentication.toHexString(Authentication.hash(password, salt));
        if (!user.getEmail().equals(email) || !user.getPassword().equals(password)) {
            context.redirect("/login");
            context.alert("Invalid email or password!");
            return;
        }
        userManager.loginUser(session, user);
        context.redirect("/");
        context.alert("Logged in!");
    }
}
