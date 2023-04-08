package id.javan.user.event.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import id.javan.user.converter.UserConverter;
import id.javan.user.dto.UserDTO;
import id.javan.user.entity.User;

@Service
public class UserUpdatedPublisher {
  private static final Logger logger = LoggerFactory.getLogger(UserUpdatedPublisher.class);
  
  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Autowired
  private UserConverter userConverter;

  @Value("userservice")
  private String exchange;

  @Value("user.updated")
  private String routingkey;

  public void publish(User user) {
    UserDTO userDTO = userConverter.EntityToForm(user);
    rabbitTemplate.convertAndSend(exchange, routingkey, userDTO);
    logger.info("Send msg : {}", user);
  }
}
