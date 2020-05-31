package cz.adastra.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person", schema = "public")
@NoArgsConstructor
@Data
public class Person implements Serializable {

  @Id
  @SequenceGenerator(schema = "public", name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
  @JsonIgnore
  private long id;
  @Column
  private String name;
  @Column
  private String surname;
}
