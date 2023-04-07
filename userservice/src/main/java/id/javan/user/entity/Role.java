package id.javan.user.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter 
@Getter
@Table(name = "roles")
public class Role {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private RoleEnum name;
}
