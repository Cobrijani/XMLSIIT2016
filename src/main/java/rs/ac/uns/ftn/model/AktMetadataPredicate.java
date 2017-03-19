package rs.ac.uns.ftn.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

/**
 * Created by SBratic on 3/19/2017.
 */
@Getter
@Setter
public class AktMetadataPredicate implements SearchPredicate {

  public String searchQuery;

  public Long dateCreatedFromTimestamp;

  public Long dateCreatedToTimestamp;

  private AktMetadataPredicate() {
  }

  public static AktMetadataPredicate of(WebRequest webRequest) {
    AktMetadataPredicate predicate = new AktMetadataPredicate();
    predicate.constructFromWebRequest(webRequest);
    return predicate;
  }

  @Override
  public void constructFromWebRequest(WebRequest webRequest) {
    Optional.ofNullable(webRequest.getParameter("q"))
      .ifPresent(this::setSearchQuery);
    Optional.ofNullable(webRequest.getParameter("from"))
      .map(Long::parseLong)
      .ifPresent(this::setDateCreatedFromTimestamp);
    Optional.ofNullable(webRequest.getParameter("to"))
      .map(Long::parseLong)
      .ifPresent(this::setDateCreatedToTimestamp);
  }
}
