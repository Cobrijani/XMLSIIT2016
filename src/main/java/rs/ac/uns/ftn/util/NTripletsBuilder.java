package rs.ac.uns.ftn.util;

import com.marklogic.client.io.StringHandle;

/**
 * Created by SBratic on 3/5/2017.
 */
public class NTripletsBuilder {


  private StringBuilder builder;

  private NTripletsBuilder() {
    builder = new StringBuilder();
  }

  public static NTripletsBuilder newInstance() {
    return new NTripletsBuilder();
  }

  private void append(String data) {
    builder.append("<").append(data).append(">");
  }

  public NTripletsBuilder subject(String subject) {
    append(subject);
    builder.append(" ");
    return this;
  }

  public NTripletsBuilder predicate(String pred) {
    append(pred);
    builder.append(" ");
    return this;
  }

  public NTripletsBuilder object(String object) {
    append(object);
    builder.append(" ").append(".");
    return this;
  }

  public StringHandle buildTriplet() {
    return new StringHandle();
  }

}
