package id.javan.tax.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import id.javan.tax.dto.UserDTO;
import id.javan.tax.service.UserService;

@Component
public class UserCreatedListener {
  private static final Logger logger = LoggerFactory.getLogger(UserCreatedListener.class);

  @Autowired
  private UserService userService;
  
  @RabbitListener(queues = "userservice.user.created")
  public void onMessageReceived(UserDTO userDTO) {
    logger.info("Received message from rmq : {}", userDTO);
    
    userService.createUser(userDTO);
  }
}
