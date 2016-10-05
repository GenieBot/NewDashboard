package io.sponges.bot.dashboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public final class Configuration {

    private final File file;
    private final JSONObject json;

    public Configuration(File file) throws IOException {
        this.file = file;
        this.json = load();
    }

    private JSONObject load() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return new JSONObject(builder.toString());
        }
    }

    public boolean isNull(String key) throws JSONException {
        return json.isNull(key);
    }

    public Object get(String key) throws JSONException {
        return json.get(key);
    }

    public JSONObject getJSONObject(String key) throws JSONException {
        return json.getJSONObject(key);
    }

    public JSONArray getJSONArray(String key) throws JSONException {
        return json.getJSONArray(key);
    }
}
