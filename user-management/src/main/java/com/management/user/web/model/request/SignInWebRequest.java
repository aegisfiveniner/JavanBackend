package com.management.user.web.model.request;

import com.management.user.validation.UsernameAndPasswordMustMatch;
import com.management.user.validation.data.SignInVerificationData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UsernameAndPasswordMustMatch(message = "Username or Password mismatch!")
public class SignInWebRequest implements SignInVerificationData {

    private String username;

    private String password;

}
