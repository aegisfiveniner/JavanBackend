package com.alurkerja.evenlistener;

import com.alurkerja.crud.user.UserDto;
import com.alurkerja.crud.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCreatedListener {

    @Autowired
    private UserService userService;

    @RabbitListener(queues = "taxservice.user.created")
    public void onMessageReceived(UserDto userDTO) {
        log.info("Received message from rmq : {}", userDTO);
        try {
            userService.createFromDto(userDTO);
        } catch (Exception e) {
            throw e;
        }
    }
}