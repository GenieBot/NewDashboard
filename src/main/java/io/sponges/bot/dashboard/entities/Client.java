package io.sponges.bot.dashboard.entities;

import io.sponges.bot.dashboard.dao.ClientDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Client implements Serializable {

    private final ClientDAO clientDAO;
    private final UUID uuid;

    Client(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
        this.uuid = clientDAO.getId();
    }

    public UUID getId() {
        return uuid;
    }

    public UUID getBotId() {
        return clientDAO.getBotId();
    }

    public AuthMethod getAuthMethod() {
        return AuthMethod.getMethod(clientDAO.getAuthMethod());
    }

    public String getName() {
        return clientDAO.getClientName();
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", uuid);
        map.put("name", getName());
        return map;
    }

    public enum AuthMethod {
        OAUTH(0), TOKEN(1);

        private final int id;

        AuthMethod(int id) {
            this.id = id;
        }

        private static AuthMethod getMethod(int id) {
            for (AuthMethod method : values()) {
                if (method.id == id) {
                    return method;
                }
            }
            return null;
        }
    }
}
