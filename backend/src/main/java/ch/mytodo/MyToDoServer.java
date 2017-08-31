package ch.mytodo;

import ch.mytodo.api.DebugService;
import ch.mytodo.impl.PersistenceServiceImpl;
import ch.mytodo.impl.ToDoServiceImpl;
import ch.mytodo.jooq.codegen.tables.pojos.ToDoList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;
import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class MyToDoServer {

    private DebugService debugService;
    private ToDoServiceImpl toDoService;
    private static Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();



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
            response.header("Access-Control-Allow-Origin", "http://localhost:4200");
            List<ToDoList> allToDoLists = toDoService.getAllToDoLists();
            return gson.toJson(allToDoLists);
        });
        get("/todo/:id", (request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "http://localhost:4200");
            String id = request.params(":id");
            Optional<ToDoList> toDoListById = toDoService.getToDoListById(parseLong(id));
            return toDoListById
                    .map(gson::toJson)
                    .orElse("{}");
        });
        get("/ping", (req, res) -> debugService.ping());
        get("/persistence", (request, response) -> {
            response.type("application/json");
            return debugService.schemaVersionInfo();
        });
    }
}
