package rs.ac.uns.ftn.model;

import org.springframework.web.context.request.WebRequest;

/**
 * Created by SBratic on 3/19/2017.
 */
public interface SearchPredicate {

  void constructFromWebRequest(WebRequest webRequest);

}
