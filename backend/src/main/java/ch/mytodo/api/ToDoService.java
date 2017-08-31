package ch.mytodo.api;

import ch.mytodo.jooq.codegen.tables.pojos.ToDo;
import ch.mytodo.jooq.codegen.tables.pojos.ToDoList;

import java.util.List;
import java.util.Optional;

public interface ToDoService {
    Optional<ToDoList> getToDoListById(Long tolistId);

    List<ToDoList> getAllToDoLists();

    List<ToDo> getAllToDos();

    ToDo create(ToDo todo);
}
