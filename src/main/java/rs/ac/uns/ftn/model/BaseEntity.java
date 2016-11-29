package rs.ac.uns.ftn.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by SBratic on 10/27/2016.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
