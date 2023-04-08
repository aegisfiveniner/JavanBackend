package id.javan.user.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.javan.user.dto.UserDTO;
import id.javan.user.entity.Role;
import id.javan.user.entity.RoleEnum;
import id.javan.user.entity.User;
import id.javan.user.repository.RoleRepository;

public class UserConverter {
  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private RoleRepository roleRepository;

  public User FormToEntity(UserDTO userDTO) {
    User user = new User(
      userDTO.getUsername(), 
      userDTO.getEmail(),
      encoder.encode(userDTO.getPassword()));

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
    
    return user;
  }

  public UserDTO EntityToForm(User entity) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(entity.getId());
    userDTO.setUsername(entity.getUsername());
    userDTO.setEmail(entity.getEmail());
    userDTO.setPassword(entity.getPassword());
    Set<String> userRoles = new HashSet<>();
    entity.getRoles().forEach(role -> {
      switch (role.getName()) {
        case ROLE_ADMIN:
          userRoles.add("admin");
          break;
        case ROLE_APPROVER:
          userRoles.add("approver");
          break;
        case ROLE_CHECKER:
          userRoles.add("checker");
          break;
        default:
          userRoles.add("maker");
        }
    });
    userDTO.setRole(userRoles);
    return userDTO;
  }
}
