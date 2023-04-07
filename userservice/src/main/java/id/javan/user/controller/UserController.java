package id.javan.user.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.javan.user.entity.User;
import id.javan.user.service.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<Object> createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping("/users/{id}")
  public Optional<User> findUserById(@PathVariable Integer id) {
    return userService.findUserById(id);
  }

  @PatchMapping("/users/{id}")
  public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody User user) {
    return userService.updateUser(user);
  }
}
