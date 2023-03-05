package com.management.user.web.controller;

import com.management.user.enums.TaxRole;
import com.management.user.service.UserService;
import com.management.user.validation.UserIdMustExist;
import com.management.user.validation.UserRoleMustAdmin;
import com.management.user.validation.UserTaxRoleMustMatch;
import com.management.user.web.model.request.InputTaxReportWebRequest;
import com.management.user.web.model.request.SignInWebRequest;
import com.management.user.web.model.request.CreateUserWebRequest;
import com.management.user.web.model.request.UpdateUserWebRequest;
import com.management.user.web.model.response.InputTaxReportWebResponse;
import com.management.user.web.model.response.Response;
import com.management.user.web.model.response.SignInWebResponse;
import com.management.user.web.model.response.UserWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {


    @Autowired
    private UserService userService;

    /**
     *USER CRUD & LOGIN
     */

    @PostMapping(
            path = "/_login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<SignInWebResponse>> signInUser(@RequestBody SignInWebRequest webRequest) {

        Response<SignInWebResponse> signInWebResponse = userService.signInUser(webRequest);

        return new ResponseEntity<>(signInWebResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(
            path = "/_register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<UserWebResponse>> registerUser(@RequestHeader @UserRoleMustAdmin(message = "Unauthorized", propName = "token") String token,
                                                                  @RequestBody @Valid CreateUserWebRequest webRequest) {

        Response<UserWebResponse> userWebResponse = userService.registerUser(webRequest);

        return new ResponseEntity<>(userWebResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(
            path = "/_get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<List<UserWebResponse>>> getUsers(@RequestHeader @UserRoleMustAdmin(message = "Unauthorized", propName = "token") String token) {

        Response<List<UserWebResponse>> userWebResponses = userService.getUsers();

        return new ResponseEntity<>(userWebResponses, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(
            path = "/{id}/_update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<UserWebResponse>> updateUser(@RequestHeader @UserRoleMustAdmin(message = "Unauthorized", propName = "token") String token,
                                                                @PathVariable @UserIdMustExist(message = "Id Must Exist", propName = "id") Long id,
                                                                @RequestBody @Valid UpdateUserWebRequest webRequest) {

        Response<UserWebResponse> userWebResponse = userService.updateUser(id, webRequest);

        return new ResponseEntity<>(userWebResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(
            path = "/{id}/_delete",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<Boolean>> deleteUser(@RequestHeader @UserRoleMustAdmin(message = "Unauthorized", propName = "token") String token,
                                                                @PathVariable @UserIdMustExist(message = "Id Must Exist", propName = "id") Long id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(Response.<Boolean>builder().data(true).status(HttpStatus.OK.value()).build(), new HttpHeaders(), HttpStatus.OK);
    }

}
