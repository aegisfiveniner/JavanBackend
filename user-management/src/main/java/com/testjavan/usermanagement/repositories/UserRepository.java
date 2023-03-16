package com.testjavan.usermanagement.repositories;

import java.util.Date;

//Made 23/11/2022 09:29

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testjavan.usermanagement.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query(value = "SELECT * FROM user WHERE id_user  = ?1", nativeQuery = true)
  public User findEmployeeById(long idUser);

  @Query(value = "SELECT * FROM Employee ORDER BY employee_name", nativeQuery = true)
  public List<User> findEmployee();

  @Query(value = "SELECT * FROM user WHERE username  = :username", nativeQuery = true)
  public User findEmployeeByUsername(@Param("username") String username);

  @Query(value = "SELECT * FROM Employee WHERE email  = :email", nativeQuery = true)
  public User findEmployeeByEmail(@Param("email") String email);

  @Query(nativeQuery = true, value = "SELECT employee_name FROM employee WHERE id_employee = ?1")
  String getNameByIdEmployee(Long id);

  @Query(nativeQuery = true, value = "SELECT created_date FROM employee WHERE id_employee = ?1")
  Date getDateByIdEmployee(Long id);

  @Query(nativeQuery = true, value = "SELECT email FROM employee WHERE id_employee = ?1")
  String getEmailByIdEmployee(Long id);

  @Query(nativeQuery = true, value = "SELECT email FROM employee WHERE id_role = 3 AND is_deleted = false"
      + " UNION "
      + " SELECT email FROM employee "
      + " JOIN watch "
      + " ON employee.id_employee = watch.id_employee "
      + " WHERE watch.id_watch_employee = ?1 AND employee.is_deleted=false")
  List<String> getAllEmailSpv();

  @Query(nativeQuery = true, value = "SELECT email FROM employee e LEFT JOIN role r ON e.id_role = r.id_role WHERE r.role_name = :role")
  List<String> findEmailByRole(@Param(value = "role") String role);

  @Query(nativeQuery = true, value = "SELECT e.* FROM employee e LEFT JOIN watch w ON e.id_employee = w.id_employee WHERE w.id_watch_employee = :id")
  List<User> findEmployeeByNotifyBy(Long id);
}