package io.sponges.bot.dashboard.controllers.account;

import io.sponges.bot.dashboard.*;
import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.entities.User;
import spark.Request;

public class ChangeEmailController extends Controller {

    public ChangeEmailController() {
        super("/account/email", true);
    }

    @Route(method = Method.POST)
    public void post(Context context) {
        Request request = context.getRequest();
        String password = request.queryParams("password");
        String email = request.queryParams("email");
        String confirmEmail = request.queryParams("confirm_email");
        if (!valid(password, email, confirmEmail)) {
            context.alert("Invalid password, email or confirm email.");
            context.redirect("/account");
            return;
        }
        if (!email.equals(confirmEmail)) {
            context.alert("Emails do not match!");
            context.redirect("/account");
            return;
        }
        User user = context.getUser();
        password = Authentication.toHexString(Authentication.hash(password, user.getSalt()));
        if (!user.getPassword().equals(password)) {
            context.alert("Incorrect password!");
            context.redirect("/account");
            return;
        }
        UserCredentialsDAO dao = user.getUserCredentialsDAO();
        dao.setEmail(email);
        try {
            dao.update();
        } catch (Exception e) {
            e.printStackTrace();
            context.alert("Something went wrong!");
            context.redirect("/account");
            return;
        }
        context.alert("Email address updated!");
        context.redirect("/account");
    }
}
