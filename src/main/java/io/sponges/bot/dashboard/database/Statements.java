package io.sponges.bot.dashboard.database;

public enum Statements {

    // ddl
    CREATE_CLIENTS_TABLE("ddl/create_clients_table.sql"),
    CREATE_USERS_TABLE("ddl/create_users_table.sql"),
    CREATE_USER_CREDENTIALS_TABLE("ddl/create_user_credentials_table.sql"),
    CREATE_NETWORKS_TABLE("ddl/create_networks_table.sql"),
    CREATE_CLIENT_USERS_TABLE("ddl/create_client_users_table.sql"),
    CREATE_OAUTH_TABLE("ddl/create_oauth_table.sql"),

    // dml
    INSERT_CLIENT("dml/insert/insert_client.sql"),
    INSERT_CLIENT_USER("dml/insert/insert_client_user.sql"),
    INSERT_NETWORK("dml/insert/insert_network.sql"),
    INSERT_USER("dml/insert/insert_user.sql"),
    INSERT_USER_CREDENTIALS("dml/insert/insert_user_credentials.sql"),
    INSERT_OAUTH("dml/insert/insert_oauth.sql"),

    SELECT_CLIENT_ID("dml/select/select_client_id.sql"),
    SELECT_CLIENT("dml/select/select_client.sql"),
    SELECT_USER_BY_EMAIL("dml/select/select_user_by_email.sql"),
    SELECT_USER_CREDENTIALS("dml/select/select_user_credentials.sql"),
    SELECT_NETWORK_ID("dml/select/select_network_id.sql"),
    SELECT_NETWORK("dml/select/select_network.sql"),
    SELECT_CLIENT_USER_ID("dml/select/select_client_user_id.sql"),
    SELECT_CLIENT_USER("dml/select/select_client_user.sql"),
    SELECT_OAUTH("dml/select/select_oauth.sql"),

    UPDATE_CLIENT_AUTH_METHOD("dml/update/update_client_auth_method.sql"),
    UPDATE_USER_CREDENTIALS("dml/update/update_user_credentials.sql"),
    UPDATE_OAUTH("dml/update/update_oauth.sql")

    ;

    private final String file;

    private String content = null;

    Statements(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return content;
    }

    public String getFile() {
        return file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}