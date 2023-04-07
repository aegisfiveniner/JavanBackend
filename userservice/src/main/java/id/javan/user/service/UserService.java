package id.javan.user.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.javan.user.entity.User;
import id.javan.user.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public ResponseEntity<Object> createUser(User user) {
    User savedUser = userRepository.save(user);
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(savedUser.getId())
      .toUri();
    return ResponseEntity.created(location).build();
  }

  public Optional<User> findUserById(Integer id) {
    return userRepository.findById(id);
  }

  public String deleteUserById(Integer id) {
    userRepository.deleteById(id);
    return "Successfully deleted user with id: " + id;
  }

  public ResponseEntity<Object> updateUser(User user) {
    User savedUser = userRepository.save(user);
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(savedUser.getId())
      .toUri();
    return ResponseEntity.created(location).build();
  }
}