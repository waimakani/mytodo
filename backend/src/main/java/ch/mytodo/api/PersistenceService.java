package ch.mytodo.api;

import org.jooq.DSLContext;

import java.util.function.Function;

public interface PersistenceService {
    <T> T doWithAutoCommit(Function<DSLContext, T> doWithDSLContext);
}
