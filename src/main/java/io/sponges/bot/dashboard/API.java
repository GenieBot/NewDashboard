package io.sponges.bot.dashboard;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.json.JSONObject;

public class API {

    private static final String API_KEY = "dummy";
    private static final String BASE_URL = "http://localhost:4568/api/v1/";

    public enum Endpoint {

        CLIENTS("clients");

        private final String endpoint;

        Endpoint(String endpoint) {
            this.endpoint = BASE_URL + endpoint;
        }

        private String appendKey(String endpoint) {
            return endpoint + "?key=" + API_KEY;
        }

        private String getEndpoint(Object... parameters) {
            String endpoint = this.endpoint;
            if (parameters != null && parameters.length > 0) {
                endpoint = String.format(endpoint, parameters);
            }
            return endpoint;
        }

        private String getEndpointWithKey(Object... parameters) {
            String endpoint = this.endpoint;
            if (parameters != null && parameters.length > 0) {
                endpoint = String.format(endpoint, parameters);
            }
            return appendKey(endpoint);
        }

        public GetRequest get(Object... parameters) {
            return Unirest.get(getEndpointWithKey(parameters));
        }

        public GetRequest head(Object... parameters) {
            return Unirest.head(getEndpointWithKey(parameters));
        }

        public HttpRequestWithBody patch(Object... parameters) {
            return Unirest.patch(getEndpoint(parameters));
        }

        public HttpRequestWithBody post(Object... parameters) {
            return Unirest.post(getEndpoint(parameters));
        }

        public HttpRequestWithBody put(Object... parameters) {
            return Unirest.put(getEndpoint(parameters));
        }

        public HttpRequestWithBody delete(Object... parameters) {
            return Unirest.delete(getEndpoint(parameters));
        }

        public HttpRequestWithBody options(Object... parameters) {
            return Unirest.options(getEndpoint(parameters));
        }

    }

    public static JSONObject appendAPIKey(JSONObject json) {
        return json.put("key", API_KEY);
    }

}
