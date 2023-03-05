package com.management.user.service.impl;

import com.management.user.authentication.JwtAuthentication;
import com.management.user.entity.User;
import com.management.user.repository.UserRepository;
import com.management.user.service.UserService;
import com.management.user.web.model.request.CreateUserWebRequest;
import com.management.user.web.model.request.InputTaxReportWebRequest;
import com.management.user.web.model.request.SignInWebRequest;
import com.management.user.web.model.request.UpdateUserWebRequest;
import com.management.user.web.model.response.InputTaxReportWebResponse;
import com.management.user.web.model.response.Response;
import com.management.user.web.model.response.SignInWebResponse;
import com.management.user.web.model.response.UserWebResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtAuthentication jwtAuthentication;

    @Override
    public Response<UserWebResponse> registerUser(CreateUserWebRequest request) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = User.builder().build();
        BeanUtils.copyProperties(request, user, "password");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return toWebResponse(userRepository.save(user));
    }

    @Override
    public Response<SignInWebResponse> signInUser(SignInWebRequest webRequest) {
        User user = userRepository.findByUsername(webRequest.getUsername());
        String jwt = jwtAuthentication.createToken(user);
        return toWebResponse(user, jwt);
    }

    @Override
    public Response<List<UserWebResponse>> getUsers() {
        List<User> users = userRepository.findAll();
        return toWebResponse(users);
    }

    @Override
    public Response<UserWebResponse> updateUser(Long id, UpdateUserWebRequest webRequest) {
        User user = userRepository.findById(id).get();
        BeanUtils.copyProperties(webRequest, user);
        return toWebResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private Response<List<UserWebResponse>> toWebResponse(List<User> users) {
        List<UserWebResponse> userWebResponses = new ArrayList<>();
        users.forEach(user -> {
            UserWebResponse userWebResponse = UserWebResponse.builder().build();
            BeanUtils.copyProperties(user, userWebResponse);
            userWebResponses.add(userWebResponse);
        });
        return Response.<List<UserWebResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(userWebResponses)
                .build();
    }

    private Response<UserWebResponse> toWebResponse(User user) {
        UserWebResponse userWebResponse = UserWebResponse.builder().build();
        BeanUtils.copyProperties(user, userWebResponse);
        return Response.<UserWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(userWebResponse)
                .build();
    }

    private Response<SignInWebResponse> toWebResponse(User user, String token) {
        SignInWebResponse signInWebResponse = SignInWebResponse.builder().build();
        BeanUtils.copyProperties(user, signInWebResponse);
        signInWebResponse.setToken(token);
        return Response.<SignInWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(signInWebResponse)
                .build();
    }
}
