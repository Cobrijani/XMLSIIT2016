package rs.ac.uns.ftn.services;

import java.util.UUID;

/**
 * Created by SBratic on 1/18/2017.
 */
public interface IdentifierGenerator {
  String generateIdentity();

  UUID generateUUID();
}
