package io.sponges.bot.dashboard;

import io.sponges.bot.dashboard.database.PostgreSQL;
import io.sponges.bot.dashboard.database.Database;

import java.io.IOException;

public final class Dashboard {

    public static void main(String[] args) {
        try {
            Database database = new PostgreSQL();
            Server server = new Server();
            MasterController masterController = new MasterController(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
