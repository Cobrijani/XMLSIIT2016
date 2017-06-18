package rs.ac.uns.ftn.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Stefan Bratić <cobrijani@gmail.com>
 *         Created on 6/18/2017.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Pair<T1, T2> {

  private final T1 first;

  private final T2 second;

  public Pair(T1 first, T2 second) {
    this.first = first;
    this.second = second;
  }
}
