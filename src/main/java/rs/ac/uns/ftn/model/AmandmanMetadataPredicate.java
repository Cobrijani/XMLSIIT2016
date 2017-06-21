package rs.ac.uns.ftn.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

/**
 * Created by Arsa on 6/12/2017.
 */
@Getter
@Setter
public class AmandmanMetadataPredicate implements SearchPredicate {

  private String searchQuery;

  private Long dateCreatedFromTimestamp;

  private Long dateCreatedToTimestamp;

  private String state;

  private String aktId;

  private boolean owned = false;

  private AmandmanMetadataPredicate() {
  }

  public static AmandmanMetadataPredicate of(WebRequest webRequest) {
    AmandmanMetadataPredicate predicate = new AmandmanMetadataPredicate();
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
    Optional.ofNullable(webRequest.getParameter("self"))
      .map(Boolean::parseBoolean)
      .ifPresent(this::setOwned);
    Optional.ofNullable(webRequest.getParameter("state"))
      .filter(x -> !x.trim().equals(""))
      .ifPresent(this::setState);
    Optional.ofNullable(webRequest.getParameter("aktId"))
      .filter(x -> !x.trim().equals(""))
      .ifPresent(this::setAktId);
  }
}
