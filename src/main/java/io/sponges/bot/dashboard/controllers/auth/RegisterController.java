package io.sponges.bot.dashboard.controllers.auth;

import io.sponges.bot.dashboard.*;
import io.sponges.bot.dashboard.entities.User;
import io.sponges.bot.dashboard.entities.UserManager;
import spark.ModelAndView;
import spark.Request;
import spark.Session;

public class RegisterController extends Controller {

    private final UserManager userManager;

    public RegisterController(UserManager userManager) {
        super("/register", false);
        this.userManager = userManager;
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        return context.createView("auth/register.ftl");
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
        String confirmPassword = request.queryParams("confirm_password");
        if (!valid(email, password, confirmPassword)) {
            context.redirect("/register");
            context.alert("Invalid email, password or confirm password fields!");
            return;
        }
        if (!password.equals(confirmPassword)) {
            context.redirect("/register");
            context.alert("Passwords do not match!");
            return;
        }
        User user;
        try {
            user = userManager.loadUser(session, email);
        } catch (Exception e) {
            e.printStackTrace();
            context.redirect("/register");
            context.alert("Something went wrong!");
            return;
        }
        if (user != null) {
            context.redirect("/register");
            context.alert("Email already exists!");
            return;
        }
        byte[] salt = Authentication.salt();
        password = Authentication.toHexString(Authentication.hash(password, salt));
        try {
            userManager.createUser(session, email, password, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.redirect("/login");
        context.alert("User created! Please login.");
    }
}
