/*
 * This file is generated by jOOQ.
*/
package ch.mytodo.jooq.codegen.tables;


import ch.mytodo.jooq.codegen.Keys;
import ch.mytodo.jooq.codegen.Public;
import ch.mytodo.jooq.codegen.tables.records.ToDoRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ToDo extends TableImpl<ToDoRecord> {

    private static final long serialVersionUID = -925265532;

    /**
     * The reference instance of <code>PUBLIC.TO_DO</code>
     */
    public static final ToDo TO_DO = new ToDo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ToDoRecord> getRecordType() {
        return ToDoRecord.class;
    }

    /**
     * The column <code>PUBLIC.TO_DO.TO_DO_NO</code>.
     */
    public final TableField<ToDoRecord, Long> TO_DO_NO = createField("TO_DO_NO", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TO_DO.TO_DO_UUID</code>.
     */
    public final TableField<ToDoRecord, UUID> TO_DO_UUID = createField("TO_DO_UUID", org.jooq.impl.SQLDataType.UUID.defaultValue(org.jooq.impl.DSL.field("RANDOM_UUID()", org.jooq.impl.SQLDataType.UUID)), this, "");

    /**
     * The column <code>PUBLIC.TO_DO.NAME</code>.
     */
    public final TableField<ToDoRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(60), this, "");

    /**
     * The column <code>PUBLIC.TO_DO.DESCRIPTION</code>.
     */
    public final TableField<ToDoRecord, String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>PUBLIC.TO_DO.TO_DO_LIST_NO</code>.
     */
    public final TableField<ToDoRecord, Integer> TO_DO_LIST_NO = createField("TO_DO_LIST_NO", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>PUBLIC.TO_DO</code> table reference
     */
    public ToDo() {
        this("TO_DO", null);
    }

    /**
     * Create an aliased <code>PUBLIC.TO_DO</code> table reference
     */
    public ToDo(String alias) {
        this(alias, TO_DO);
    }

    private ToDo(String alias, Table<ToDoRecord> aliased) {
        this(alias, aliased, null);
    }

    private ToDo(String alias, Table<ToDoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ToDoRecord, Long> getIdentity() {
        return Keys.IDENTITY_TO_DO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ToDoRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_4;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ToDoRecord>> getKeys() {
        return Arrays.<UniqueKey<ToDoRecord>>asList(Keys.CONSTRAINT_4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ToDoRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ToDoRecord, ?>>asList(Keys.CONSTRAINT_4C);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToDo as(String alias) {
        return new ToDo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ToDo rename(String name) {
        return new ToDo(name, null);
    }
}