package rs.ac.uns.ftn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "id", unique = true, nullable = false)
  protected long id;

  @CreatedBy
  protected String createdBy;

  @CreatedDate
  protected ZonedDateTime created;

  @LastModifiedBy
  protected String lastModifiedBy;

  @LastModifiedDate
  protected ZonedDateTime lastModified;


}
