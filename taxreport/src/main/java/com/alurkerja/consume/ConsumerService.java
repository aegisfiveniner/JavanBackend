package com.alurkerja.consume;

import com.alurkerja.crud.user.User;
import com.alurkerja.crud.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

    private final UserRepository userRepository;

    @Autowired
    public ConsumerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(User user) {
        User save = userRepository.save(user);
        log.info("persisted " + save);
        log.info("User Details Received is.. " + user);
    }

}