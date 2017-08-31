package ch.mytodo.impl;

import ch.mytodo.api.PersistenceService;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

import static ch.mytodo.impl.PersistenceConstants.*;
import static org.jooq.SQLDialect.H2;

public class PersistenceServiceImpl implements PersistenceService {
    @Override
    public <T> T doWithAutoCommit(Function<DSLContext, T> doWithDSLContext) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD)) {
            DSLContext dslContext = DSL.using(conn, H2);
            return doWithDSLContext.apply(dslContext);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
