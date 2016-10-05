package io.sponges.bot.dashboard;

import io.sponges.bot.dashboard.entities.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public class ContextImpl implements Context {

    private final Request request;
    private final Response response;
    private final Model model;

    private User user = null;

    public ContextImpl(Request request, Response response, Model model) {
        this.request = request;
        this.response = response;
        this.model = model;
    }

    @Override
    public void alert(String alert) {
        getSession().attribute(ALERT_SESSION_KEY, alert);
    }

    @Override
    public boolean hasAlert() {
        return getSession().attributes().contains(ALERT_SESSION_KEY);
    }

    @Override
    public String getAlert() {
        return getSession().attribute(ALERT_SESSION_KEY);
    }

    @Override
    public String error(int code, String error) {
        response.status(code);
        alert(error);
        return error;
    }

    @Override
    public void redirect(String url) {
        response.redirect(url);
    }

    @Override
    public ModelAndView createView(String template) {
        model.withMapping("logged_in", isLoggedIn());
        if (hasAlert()) {
            model.withMapping("alert", getAlert());
            getSession().removeAttribute(ALERT_SESSION_KEY);
        }
        return new ModelAndView(model.build(), template);
    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    public Response getResponse() {
        return response;
    }

    @Override
    public Session getSession() {
        return request.session();
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
