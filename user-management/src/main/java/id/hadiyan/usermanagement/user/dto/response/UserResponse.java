package id.hadiyan.usermanagement.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private String id;
    private String username;
    private String name;
    private String email;
    private String role;
}
