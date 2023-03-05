package id.hadiyan.usermanagement.user.dto;

import id.hadiyan.usermanagement.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConsumerDto {
    private String action;
    private UserResponse data;
}
