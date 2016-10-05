package io.sponges.bot.dashboard;

import io.sponges.bot.dashboard.database.PostgreSQL;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.entities.ClientManager;
import io.sponges.bot.dashboard.entities.ClientUserManager;
import io.sponges.bot.dashboard.entities.UserManager;

import java.io.File;
import java.io.IOException;

public final class Dashboard {

    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration(new File("config.json"));
            Database database = new PostgreSQL();
            Server server = new Server();
            UserManager userManager = new UserManager(database);
            ClientManager clientManager = new ClientManager(database);
            ClientUserManager clientUserManager = new ClientUserManager(configuration, database);
            MasterController masterController = new MasterController(server, configuration, database, userManager,
                    clientManager, clientUserManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
