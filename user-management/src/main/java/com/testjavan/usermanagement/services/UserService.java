package com.testjavan.usermanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testjavan.usermanagement.dtos.UserDTO;
import com.testjavan.usermanagement.models.User;
import com.testjavan.usermanagement.repositories.UserRepository;

@Service
public class UserService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UserRepository employeeRepo;

    public List<User> readAllEmployees() {
        return employeeRepo.findAll();
    }

    public List<UserDTO> readAllEmployeesDTO() {
        List<UserDTO> listUserDTO = new ArrayList<UserDTO>();

        readAllEmployees().forEach(employee -> {
            listUserDTO.add(modelMapper.map(employee, UserDTO.class));
        });

        return listUserDTO;
    }

    public User getEmployeeById(long id) {
        return employeeRepo.findEmployeeById(id);
    }

    public UserDTO getEmployeeByIdDT0(long employeeId) {
        UserDTO userDTO = new UserDTO();
        User emp = getEmployeeById(employeeId);
        userDTO = modelMapper.map(emp, UserDTO.class);
        return userDTO;
    }

    public User getEmployeeByUsername(String username) {
        return employeeRepo.findEmployeeByUsername(username);
    }

    public UserDTO getEmployeeByUsernameDTO(String username) {
        UserDTO userDTO = new UserDTO();
        User emp = getEmployeeByUsername(username);
        userDTO = modelMapper.map(emp, UserDTO.class);
        return userDTO;
    }

    public User getEmployeeByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }

    public UserDTO getEmployeeByEmailDTO(String email) {
        UserDTO userDTO = new UserDTO();
        User emp = getEmployeeByEmail(email);
        userDTO = modelMapper.map(emp, UserDTO.class);
        return userDTO;
    }

}
