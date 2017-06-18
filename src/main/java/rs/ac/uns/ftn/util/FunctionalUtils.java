package rs.ac.uns.ftn.util;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Stefan Bratić <cobrijani@gmail.com>
 *         Created on 6/18/2017.
 */
public class FunctionalUtils {


  private FunctionalUtils() {
    throw new UnsupportedOperationException("Cannot call constructor");
  }

  public static <T1, T2> Stream<Pair<T1, T2>> zip(List<T1> list, List<T2> list1) {
    return IntStream.range(0, Math.min(list.size(), list1.size()))
      .mapToObj(i -> new Pair<T1, T2>(list.get(i), list1.get(i)));
  }


}
