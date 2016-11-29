package rs.ac.uns.ftn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.model.Authority;

import java.util.Optional;

/**
 * Created by SBratic on 11/3/2016.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

  Optional<Authority> findByName(String name);

}
