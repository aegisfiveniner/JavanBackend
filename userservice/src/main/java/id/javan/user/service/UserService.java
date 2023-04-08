package id.javan.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.javan.user.converter.UserConverter;
import id.javan.user.dto.UserDTO;
import id.javan.user.entity.User;
import id.javan.user.event.publisher.UserCreatedPublisher;
import id.javan.user.event.publisher.UserDeletedPublisher;
import id.javan.user.event.publisher.UserUpdatedPublisher;
import id.javan.user.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private UserCreatedPublisher userCreatedPublisher;

  @Autowired
  private UserUpdatedPublisher userUpdatedPublisher;

  @Autowired
  private UserDeletedPublisher userDeletedPublisher;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User createUser(UserDTO userDTO) {
    if (userRepository.existsByUsername(userDTO.getUsername())) {
      throw new RuntimeException("Error: Username is already taken!");
    }

    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new RuntimeException("Error: Email is already in use!");
    }

    User user = userConverter.FormToEntity(userDTO);
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