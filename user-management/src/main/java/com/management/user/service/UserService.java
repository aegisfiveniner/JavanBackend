package com.management.user.service;

import com.management.user.web.model.request.InputTaxReportWebRequest;
import com.management.user.web.model.request.SignInWebRequest;
import com.management.user.web.model.request.CreateUserWebRequest;
import com.management.user.web.model.request.UpdateUserWebRequest;
import com.management.user.web.model.response.InputTaxReportWebResponse;
import com.management.user.web.model.response.Response;
import com.management.user.web.model.response.SignInWebResponse;
import com.management.user.web.model.response.UserWebResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Response<UserWebResponse> registerUser(CreateUserWebRequest request);

    Response<SignInWebResponse> signInUser(SignInWebRequest webRequest);

    Response<List<UserWebResponse>> getUsers();

    Response<UserWebResponse> updateUser(Long id, UpdateUserWebRequest webRequest);

    void deleteUser(Long id);
}
