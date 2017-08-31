package ch.mytodo.api;

import ch.mytodo.jooq.codegen.tables.pojos.SchemaVersion;
import com.google.gson.Gson;

import java.util.List;

import static ch.mytodo.jooq.codegen.Tables.SCHEMA_VERSION;

public class DebugService {

    private PersistenceService persistenceService;

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public String ping() {
        return "MyToDoSever is running";
    }

    public String schemaVersionInfo(){
        List<SchemaVersion> schemaVersions = persistenceService.doWithAutoCommit(
                dslContext -> dslContext.selectFrom(SCHEMA_VERSION)
                .orderBy(SCHEMA_VERSION.EXECUTION_TIME.desc())
                .fetchInto(SchemaVersion.class)

        );
        Gson gson = new Gson();
        return gson.toJson(schemaVersions);
    }


}
