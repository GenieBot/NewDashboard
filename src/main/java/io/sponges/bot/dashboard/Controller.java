package io.sponges.bot.dashboard;

public abstract class Controller implements DataValidator {

    private final String route;
    private final boolean auth;

    public Controller(String route, boolean auth) {
        this.route = route;
        this.auth = auth;
    }

    public String getRoute() {
        return route;
    }

    public boolean isAuth() {
        return auth;
    }
}
