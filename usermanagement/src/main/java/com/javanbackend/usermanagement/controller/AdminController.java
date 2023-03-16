package com.javanbackend.usermanagement.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javanbackend.usermanagement.request.RegisterBody;
import com.javanbackend.usermanagement.service.IUserDetails;
import com.javanbackend.usermanagement.service.UserService;
import com.javanbackend.usermanagement.model.EnumRole;
import com.javanbackend.usermanagement.model.EnumUserRole;
import com.javanbackend.usermanagement.model.User;
import com.javanbackend.usermanagement.repository.RoleRepository;
import com.javanbackend.usermanagement.repository.UserRepository;
import com.javanbackend.usermanagement.repository.UserRoleRepository;

@RestController
@RequestMapping("/api/user-management/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserService userService;

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegisterBody requestBody) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        Optional<User> existingUser = userRepository.findOneByEmail(requestBody.getEmail());

        if (existingUser.isPresent()) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Email already exists. Please use another email.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        if (!roleRepository.existsById(requestBody.getRoleId())) {
            status = HttpStatus.NOT_FOUND;
            result.put("message", "Register user fail. Role id not found.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }
        
        if (!userRoleRepository.existsById(requestBody.getUserRoleId())) {
            status = HttpStatus.NOT_FOUND;
            result.put("message", "Register user fail. User role id not found.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        if (
            requestBody.getRoleId() == roleRepository.findByName(EnumRole.ADMIN).get().getRoleId() &&
            requestBody.getUserRoleId() != userRoleRepository.findByName(EnumUserRole.ADMIN).get().getUserRoleId()
        ) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Register user fail. Your user role must be admin if you choose role ADMIN.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }
        
        if (
            requestBody.getRoleId() == roleRepository.findByName(EnumRole.USER).get().getRoleId() &&
            requestBody.getUserRoleId() == userRoleRepository.findByName(EnumUserRole.ADMIN).get().getUserRoleId()
        ) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Register user fail. Your user role cannot be ADMIN if your role is USER.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        userService.registerUser(requestBody, result, status);
        
        return new ResponseEntity<>(result, status);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list-user")
    public ResponseEntity<Object> getListUser() {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        userService.getListUser(result, status);

        return new ResponseEntity<>(result, status); 
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit/{userId}")
    public ResponseEntity<Object> editUser(@PathVariable(name = "userId") int userId, @Valid @RequestBody RegisterBody requestBody) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        Optional<User> existingUserById = userRepository.findById(userId);

        if (!existingUserById.isPresent()) {
            status = HttpStatus.NOT_FOUND;
            result.put("message", "Update user fail. User id not found.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        Optional<User> existingUser = userRepository.findOneByEmail(requestBody.getEmail());

        if (existingUser.isPresent() && userId != existingUser.get().getUserId()) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Email already exists. Please use another email.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        if (!roleRepository.existsById(requestBody.getRoleId())) {
            status = HttpStatus.NOT_FOUND;
            result.put("message", "Update user fail. Role id not found.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }
        
        if (!userRoleRepository.existsById(requestBody.getUserRoleId())) {
            status = HttpStatus.NOT_FOUND;
            result.put("message", "Update user fail. User role id not found.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        if (
            requestBody.getRoleId() == roleRepository.findByName(EnumRole.ADMIN).get().getRoleId() &&
            requestBody.getUserRoleId() != userRoleRepository.findByName(EnumUserRole.ADMIN).get().getUserRoleId()
        ) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Update user fail. Your user role must be admin if you choose role ADMIN.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }
        
        if (
            requestBody.getRoleId() == roleRepository.findByName(EnumRole.USER).get().getRoleId() &&
            requestBody.getUserRoleId() == userRoleRepository.findByName(EnumUserRole.ADMIN).get().getUserRoleId()
        ) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Update user fail. Your user role cannot be ADMIN if your role is USER.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        userService.updateUser(existingUserById.get(), requestBody, result, status);

        return new ResponseEntity<>(result, status);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        Optional<User> existingUserById = userRepository.findById(userId);

        if (!existingUserById.isPresent()) {
            status = HttpStatus.NOT_FOUND;
            result.put("message", "Delete user fail. User id not found.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        if (((IUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId() == userId) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Delete user fail. You can't delete yourself.");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        userService.deleteById(userId, result, status);

        return new ResponseEntity<>(result, status);
    }
}
