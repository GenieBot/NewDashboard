package io.sponges.bot.dashboard;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private final Map<String, Object> model = new HashMap<>();
    private final JSONObject json = new JSONObject();

    public Model withMapping(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public Model withJson(String key, Object value) {
        json.put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        model.put("json", json);
        return model;
    }

}
