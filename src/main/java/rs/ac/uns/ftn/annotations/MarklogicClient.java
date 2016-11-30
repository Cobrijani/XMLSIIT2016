package rs.ac.uns.ftn.annotations;

import java.lang.annotation.*;

/**
 * Annotations that represents marklogic client
 * Created by SBratic on 11/30/2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MarklogicClient {
}
