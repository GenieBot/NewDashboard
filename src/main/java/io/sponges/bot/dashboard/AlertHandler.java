package io.sponges.bot.dashboard;

public interface AlertHandler {

    String ALERT_SESSION_KEY = "alert";

    void alert(String alert);

    boolean hasAlert();

    String getAlert();
}
