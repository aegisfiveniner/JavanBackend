package id.javan.user.event.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserDeletedPublisher {
  private static final Logger logger = LoggerFactory.getLogger(UserDeletedPublisher.class);
  
  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Value("userservice")
  private String exchange;

  @Value("user.deleted")
  private String routingkey;

  public void publish(Long id) {
    rabbitTemplate.convertAndSend(exchange, routingkey, id);
    logger.info("Send msg : {}", id);
  }
}
