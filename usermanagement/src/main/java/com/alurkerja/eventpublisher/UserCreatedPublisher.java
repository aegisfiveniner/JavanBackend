package com.alurkerja.eventpublisher;


import com.alurkerja.crud.user.User;
import com.alurkerja.crud.user.UserDto;
import com.alurkerja.crud.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCreatedPublisher {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private UserService userService;

    @Value("userservice")
    private String exchange;

    @Value("user.created")
    private String routingkey;

    public void publish(User user) {
        rabbitTemplate.convertAndSend(exchange, routingkey, user);
        log.info("Send msg : {}", user);
    }
}