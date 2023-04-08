package id.javan.tax.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import id.javan.tax.converter.UserConverter;
import id.javan.tax.dto.UserDTO;
import id.javan.tax.entity.User;
import id.javan.tax.service.UserService;

@Component
public class UserUpdatedListener {
  private static final Logger logger = LoggerFactory.getLogger(UserUpdatedListener.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserConverter userConverter;
  
  @RabbitListener(queues = "userservice.user.updated")
  public void onMessageReceived(UserDTO userDTO) {
    logger.info("Received message from rmq : {}", userDTO);
    
    User user = userConverter.FormToEntity(userDTO);
    userService.updateUser(user);
  }
}
