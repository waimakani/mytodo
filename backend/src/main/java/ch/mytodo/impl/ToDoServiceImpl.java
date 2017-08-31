package ch.mytodo.impl;

import ch.mytodo.api.PersistenceService;
import ch.mytodo.api.ToDoService;
import ch.mytodo.jooq.codegen.tables.pojos.ToDoList;


import java.util.List;
import java.util.Optional;

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

}
