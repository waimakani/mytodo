/*
 * This file is generated by jOOQ.
*/
package ch.mytodo.jooq.codegen;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in PUBLIC
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>PUBLIC.SYSTEM_SEQUENCE_A904FD4B_B34C_4F61_8755_0337D9ADF175</code>
     */
    public static final Sequence<Long> SYSTEM_SEQUENCE_A904FD4B_B34C_4F61_8755_0337D9ADF175 = new SequenceImpl<Long>("SYSTEM_SEQUENCE_A904FD4B_B34C_4F61_8755_0337D9ADF175", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT);
}
