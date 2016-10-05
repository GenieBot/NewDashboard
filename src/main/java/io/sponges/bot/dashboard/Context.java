package io.sponges.bot.dashboard;

import io.sponges.bot.dashboard.entities.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public interface Context extends AlertHandler {

    String error(int code, String error);

    void redirect(String url);

    ModelAndView createView(String template);

    Request getRequest();

    Response getResponse();

    Session getSession();

    Model getModel();

    User getUser();

    default boolean isLoggedIn() {
        return getUser() != null;
    }
}
