package io.sponges.bot.dashboard.controllers.account;

import io.sponges.bot.dashboard.*;
import spark.ModelAndView;

public class AccountController extends Controller {

    public AccountController() {
        super("/account", true);
    }

    @Route(method = Method.GET)
    public ModelAndView get(Context context) {
        Model model = context.getModel();
        model.withMapping("user_email", context.getUser().getEmail());
        return context.createView("account/account.ftl");
    }
}
