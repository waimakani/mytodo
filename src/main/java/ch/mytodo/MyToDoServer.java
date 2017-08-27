package ch.mytodo;

import ch.mytodo.api.DebugService;
import ch.mytodo.impl.PersistenceServiceImpl;

import static spark.Spark.get;

public class MyToDoServer {

    private DebugService debugService;

    public MyToDoServer(){
        debugService = new DebugService();
        debugService.setPersistenceService(new PersistenceServiceImpl());
    }

    public void startServer(){
        get("/ping", (req, res) -> debugService.ping());
        get("/persistence", (request, response) -> {
            response.type("application/json");
            return debugService.schemaVersionInfo();
        });
    }

}
