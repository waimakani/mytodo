package ch.mytodo.api;

import ch.mytodo.jooq.codegen.tables.pojos.ToDo;
import ch.mytodo.jooq.codegen.tables.pojos.ToDoList;

import java.util.List;
import java.util.Optional;

public interface ToDoService {
    Optional<ToDoList> getToDoListById(Long tolistId);

    Optional<ToDo> getToDoById(Long todoId);

    List<ToDoList> getAllToDoLists();

    List<ToDo> getAllToDos();

    List<ToDo> getAllToDosOfList(Integer listNo);

    ToDo create(ToDo todo);

    void createList(ToDoList toDoList);

    void update(ToDo todo);
}
