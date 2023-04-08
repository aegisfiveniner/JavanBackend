package id.javan.tax.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.javan.tax.dto.UserDTO;
import id.javan.tax.entity.Role;
import id.javan.tax.entity.RoleEnum;
import id.javan.tax.entity.User;
import id.javan.tax.repository.UserRepository;
import id.javan.tax.repository.RoleRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

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

    User user = new User(
      userDTO.getUsername(), 
      userDTO.getEmail(),
      userDTO.getPassword()
    );

    Set<String> strRoles = userDTO.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role makerRole = roleRepository.findByName(RoleEnum.ROLE_MAKER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(makerRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);
          break;
        case "approver":
          Role approverRole = roleRepository.findByName(RoleEnum.ROLE_APPROVER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(approverRole);
          break;
        case "checker":
          Role checkerRole = roleRepository.findByName(RoleEnum.ROLE_CHECKER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(checkerRole);
          break;
        default:
          Role userRole = roleRepository.findByName(RoleEnum.ROLE_MAKER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }
    
    user.setRoles(roles);
    user.setId(userDTO.getId());
    
    User savedUser = userRepository.save(user);
    return savedUser;
  }

  public Optional<User> findUserById(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public String deleteUserById(Long id) {
    userRepository.deleteById(id);
    return "Successfully deleted user with id: " + id;
  }

  public User updateUser(User user) {
    User savedUser = userRepository.save(user);
    return savedUser;
  }
}