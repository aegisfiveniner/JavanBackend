package com.testjavan.usermanagement.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testjavan.usermanagement.models.ERole;
import com.testjavan.usermanagement.models.Role;
import com.testjavan.usermanagement.models.User;
import com.testjavan.usermanagement.payload.request.LoginRequest;
import com.testjavan.usermanagement.payload.request.SignupRequest;
import com.testjavan.usermanagement.payload.response.JwtResponse;
import com.testjavan.usermanagement.payload.response.MessageResponse;
import com.testjavan.usermanagement.repository.RoleRepository;
import com.testjavan.usermanagement.repository.UserRepository;
import com.testjavan.usermanagement.security.jwt.JwtUtils;
import com.testjavan.usermanagement.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!!!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!!!!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Role is not found"));
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "user maker":
					Role userMakerRole = roleRepository.findByName(ERole.ROLE_USER_MAKER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userMakerRole);

					break;
				case "user checker":
					Role userCheckerRole = roleRepository.findByName(ERole.ROLE_USER_CHECKER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userCheckerRole);

					break;
				case "user approver":
					Role userApprover = roleRepository.findByName(ERole.ROLE_USER_APPROVER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userApprover);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER_MAKER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}

