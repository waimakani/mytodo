package ch.mytodo.impl;

import ch.mytodo.api.PersistenceService;
import ch.mytodo.api.ToDoService;
import ch.mytodo.jooq.codegen.tables.pojos.ToDo;
import ch.mytodo.jooq.codegen.tables.pojos.ToDoList;
import ch.mytodo.jooq.codegen.tables.records.ToDoRecord;
import org.jooq.Result;


import java.util.List;
import java.util.Optional;

import static ch.mytodo.jooq.codegen.Tables.TO_DO;
import static ch.mytodo.jooq.codegen.Tables.TO_DO_LIST;

public class ToDoServiceImpl implements ToDoService {

    private PersistenceService persistenceService;

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Override
    public Optional<ToDoList> getToDoListById(Long toListId){
        ToDoList toDoList = persistenceService.doWithAutoCommit(dslContext ->
                dslContext.selectFrom(TO_DO_LIST)
                        .where(TO_DO_LIST.TO_DO_LIST_NO.eq(toListId))
                        .fetchOneInto(ToDoList.class)
        );
        return Optional.ofNullable(toDoList);
    }

    @Override
    public List<ToDoList> getAllToDoLists(){
        return persistenceService.doWithAutoCommit(dslContext ->
            dslContext.selectFrom(TO_DO_LIST)
                    .fetchInto(ToDoList.class)
        );
    }



    @Override
    public List<ToDo> getAllToDos(){
        return persistenceService.doWithAutoCommit(dslContext ->
                dslContext.selectFrom(TO_DO)
                        .fetchInto(ToDo.class)
        );
    }

    @Override
    public List<ToDo> getAllToDosOfList(Integer listNo) {
        return persistenceService.doWithAutoCommit(dslContext ->
                dslContext.selectFrom(TO_DO)
                        .where(TO_DO.TO_DO_LIST_NO.eq(listNo))
                        .fetchInto(ToDo.class)
        );
    }

    @Override
    public ToDo create(ToDo todo) {
        ToDoRecord toDoRecord = persistenceService.doWithAutoCommit(dslContext ->
                dslContext.insertInto(TO_DO,
                        TO_DO.NAME,
                        TO_DO.DESCRIPTION,
                        TO_DO.TO_DO_LIST_NO,
                        TO_DO.PARENT_TO_DO_NO)
                        .values(todo.getName(),
                                todo.getDescription(),
                                todo.getToDoListNo(),
                                todo.getParentToDoNo())
                        .returning(TO_DO.TO_DO_NO,
                                TO_DO.TO_DO_UUID,
                                TO_DO.NAME,
                                TO_DO.DESCRIPTION,
                                TO_DO.TO_DO_LIST_NO,
                                TO_DO.PARENT_TO_DO_NO).fetchOne()
        );

        return new ToDo(
                toDoRecord.getToDoNo(),
                toDoRecord.getToDoUuid(),
                toDoRecord.getName(),
                toDoRecord.getDescription(),
                toDoRecord.getToDoListNo(),
                toDoRecord.getParentToDoNo(),
                toDoRecord.getCompleted()
        );
    }

}
