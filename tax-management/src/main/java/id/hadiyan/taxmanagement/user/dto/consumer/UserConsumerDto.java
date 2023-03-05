package id.hadiyan.taxmanagement.user.dto.consumer;

import lombok.Data;

@Data
public class UserConsumerDto {
    private String action;
    private UserDto data;
}
