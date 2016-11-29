package rs.ac.uns.ftn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * Created by SBratic on 10/28/2016.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password", unique = false, nullable = false)
  private String password;

  @Column(name = "email", unique = false, nullable = false)
  private String email;

  @Column(name = "last_password_reset", unique = false, nullable = true)
  private ZonedDateTime lastPasswordReset;

  @ManyToMany(cascade = {ALL}, fetch = LAZY)
  @JoinTable(name = "assigned_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
  private Set<Authority> authorities = new HashSet<>();

  @Column(name = "is_enabled", unique = false, nullable = false)
  private boolean isEnabled;

}
