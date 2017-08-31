package ch.mytodo;

import ch.mytodo.api.DebugService;
import ch.mytodo.impl.PersistenceServiceImpl;
import ch.mytodo.impl.ToDoServiceImpl;
import ch.mytodo.jooq.codegen.tables.pojos.ToDoList;
import com.google.gson.Gson;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class MyToDoServer {

    private DebugService debugService;
    private ToDoServiceImpl toDoService;
    private static Gson gson = new Gson();

    public MyToDoServer(){
        debugService = new DebugService();
        debugService.setPersistenceService(new PersistenceServiceImpl());
        toDoService = new ToDoServiceImpl();
        toDoService.setPersistenceService(new PersistenceServiceImpl());
    }

    public void startServer(){
        staticFiles.location("web");

        get("/todos", (request, response) -> {
            response.type("application/json");
            List<ToDoList> allToDoLists = toDoService.getAllToDoLists();
            return gson.toJson(allToDoLists);
        });
        get("/ping", (req, res) -> debugService.ping());
        get("/persistence", (request, response) -> {
            response.type("application/json");
            return debugService.schemaVersionInfo();
        });
    }
}
