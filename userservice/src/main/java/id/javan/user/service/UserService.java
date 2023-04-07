package id.javan.user.service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.javan.user.entity.Role;
import id.javan.user.entity.RoleEnum;
import id.javan.user.entity.User;
import id.javan.user.payload.request.SignupRequest;
import id.javan.user.repository.UserRepository;
import id.javan.user.repository.RoleRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User createUser(SignupRequest user) {
    return userRepository.save(user);
  }

  public Optional<User> findUserById(Long id) {
    return userRepository.findById(id);
  }

  public String deleteUserById(Long id) {
    userRepository.deleteById(id);
    return "Successfully deleted user with id: " + id;
  }

  public User updateUser(User user) {
    return userRepository.save(user);
  }
}