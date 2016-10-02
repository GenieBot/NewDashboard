package io.sponges.bot.dashboard;

import freemarker.template.Configuration;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;

public class Server {

    private final Service service;
    private final TemplateEngine engine;

    Server() throws IOException {
        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        this.engine = new FreeMarkerEngine(config);
        this.service = Service.ignite();
        this.service.exception(Exception.class, (e, request, response) -> e.printStackTrace());
        this.service.staticFileLocation("static");
        this.service.port(4567);
    }

    public Service getService() {
        return service;
    }

    public TemplateEngine getEngine() {
        return engine;
    }
}
