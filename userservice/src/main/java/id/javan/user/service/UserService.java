package id.javan.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.javan.user.entity.User;
import id.javan.user.events.publishers.UserCreatedPublisher;
import id.javan.user.events.publishers.UserDeletedPublisher;
import id.javan.user.events.publishers.UserUpdatedPublisher;
import id.javan.user.repository.UserRepository;
import id.javan.user.repository.RoleRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserCreatedPublisher userCreatedPublisher;

  @Autowired
  UserUpdatedPublisher userUpdatedPublisher;

  @Autowired
  UserDeletedPublisher userDeletedPublisher;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User createUser(User user) {
    User savedUser = userRepository.save(user);
    userCreatedPublisher.publish(savedUser);
    return savedUser;
  }

  public Optional<User> findUserById(Long id) {
    return userRepository.findById(id);
  }

  public String deleteUserById(Long id) {
    userRepository.deleteById(id);
    userDeletedPublisher.publish(id);
    return "Successfully deleted user with id: " + id;
  }

  public User updateUser(User user) {
    User savedUser = userRepository.save(user);
    userUpdatedPublisher.publish(savedUser);
    return savedUser;
  }
}