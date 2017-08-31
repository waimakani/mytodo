package ch.mytodo;

import ch.mytodo.api.DebugService;
import ch.mytodo.impl.PersistenceServiceImpl;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import spark.*;

import java.io.InputStream;
import java.io.InputStreamReader;

import static spark.Spark.get;
import static spark.Spark.modelAndView;
import static spark.Spark.staticFiles;

public class MyToDoServer {

    private DebugService debugService;

    public MyToDoServer(){
        debugService = new DebugService();
        debugService.setPersistenceService(new PersistenceServiceImpl());
    }

    public void startServer(){
        staticFiles.location("web");

        get("/ping", (req, res) -> debugService.ping());
        get("/persistence", (request, response) -> {
            response.type("application/json");
            return debugService.schemaVersionInfo();
        });
    }
}
