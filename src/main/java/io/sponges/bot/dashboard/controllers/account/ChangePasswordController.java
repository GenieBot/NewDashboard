package io.sponges.bot.dashboard.controllers.account;

import io.sponges.bot.dashboard.*;
import io.sponges.bot.dashboard.dao.UserCredentialsDAO;
import io.sponges.bot.dashboard.entities.User;
import spark.Request;

public class ChangePasswordController extends Controller {

    public ChangePasswordController() {
        super("/account/password", true);
    }

    @Route(method = Method.POST)
    public void post(Context context) {
        Request request = context.getRequest();
        String current = request.queryParams("current_password");
        String password = request.queryParams("password");
        String confirm = request.queryParams("confirm_password");
        if (!valid(current, password, confirm)) {
            context.alert("Invalid current, new or confirmed passwords");
            context.redirect("/account");
            return;
        }
        if (!password.equals(confirm)) {
            context.alert("New password and confirm password do not match!");
            context.redirect("/account");
            return;
        }
        User user = context.getUser();
        String oldHash = Authentication.toHexString(Authentication.hash(current, user.getSalt()));
        if (!oldHash.equals(user.getPassword())) {
            context.alert("Old password is incorrect!");
            context.redirect("/account");
            return;
        }
        UserCredentialsDAO dao = user.getUserCredentialsDAO();
        byte[] salt = Authentication.salt();
        password = Authentication.toHexString(Authentication.hash(password, salt));
        dao.setPassword(password);
        dao.setSalt(salt);
        try {
            dao.update();
        } catch (Exception e) {
            e.printStackTrace();
            context.alert("Something went wrong!");
            context.redirect("/account");
            return;
        }
        context.alert("Password updated!");
        context.redirect("/account");
    }
}
