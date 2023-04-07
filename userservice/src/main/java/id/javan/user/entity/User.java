package id.javan.user.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter 
@Getter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private String role;
}