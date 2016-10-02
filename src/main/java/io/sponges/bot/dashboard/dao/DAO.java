package io.sponges.bot.dashboard.dao;

import io.sponges.bot.dashboard.database.Database;

public abstract class DAO {

    protected Database database = null;

    public void setDatabase(Database database) {
        this.database = database;
    }
}
