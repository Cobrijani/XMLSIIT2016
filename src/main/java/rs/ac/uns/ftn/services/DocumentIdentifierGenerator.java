package rs.ac.uns.ftn.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by SBratic on 1/18/2017.
 */
@Service
public class DocumentIdentifierGenerator implements IdentifierGenerator {

  @Override
  public String generateIdentity() {
    return UUID.randomUUID().toString();
  }

  @Override
  public UUID generateUUID() {
    return UUID.randomUUID();
  }
}
