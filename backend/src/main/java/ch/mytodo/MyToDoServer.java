package ch.mytodo;

import ch.mytodo.api.DebugService;
import ch.mytodo.impl.PersistenceServiceImpl;
import ch.mytodo.impl.ToDoServiceImpl;
import ch.mytodo.jooq.codegen.tables.pojos.ToDo;
import ch.mytodo.jooq.codegen.tables.pojos.ToDoList;
import com.google.gson.Gson;

import java.util.List;
import java.util.Optional;

import static spark.Spark.*;

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

        get("/todolists", (request, response) -> {
            response.type("application/json");
            List<ToDoList> allToDos = toDoService.getAllToDoLists();
            return gson.toJson(allToDos);
        });
        get("/todos", (request, response) -> {
            response.type("application/json");
            List<ToDo> allToDos = toDoService.getAllToDos();
            return gson.toJson(allToDos);
        });
        get("/todos/:id", (request, response) -> {
            Long id = Long.valueOf(request.params(":id"));
            response.type("application/json");
            Optional<String> todo = toDoService.getAllToDos()
                    .stream()
                    .filter(t -> t.getToDoNo().equals(id))
                    .map(t -> gson.toJson(t))
                    .findFirst();
            return todo.orElse(null);
        });
        put("/todos/:id", (request, response) -> {
            response.type("application/json");
            ToDo todo = gson.fromJson(request.body(), ToDo.class);
            toDoService.update(todo);
            Optional<ToDo> readBack = toDoService.getToDoById(todo.getToDoNo());
            return readBack.orElse(null);
        });
        get("/ping", (req, res) -> debugService.ping());
        get("/persistence", (request, response) -> {
            response.type("application/json");
            return debugService.schemaVersionInfo();
        });

        get("/todoList/:id",(request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "http://localhost:4200");
            String id = request.params(":id");
            if(id != null){
                Optional<ToDoList> toDoListById = toDoService.getToDoListById(Long.parseLong(id));
                return toDoListById
                        .map(gson::toJson)
                        .orElse("{}");
            }else{
                List<ToDo> allToDos = toDoService.getAllToDos();
                return gson.toJson(allToDos);
            }
        });
        post("/todolist",(request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "http://localhost:4200");
            ToDoList toDoList = gson.fromJson(request.body(), ToDoList.class);
            toDoService.createList(toDoList);
            List<ToDoList> allToDos = toDoService.getAllToDoLists();
            return gson.toJson(allToDos);
        });


        post("/todo", (req, res) -> {
            ToDo todo= gson.fromJson(req.body(), ToDo.class);
            ToDo justCreatedTodo = toDoService.create(todo);
            return gson.toJson(justCreatedTodo);
        });
    }
}
