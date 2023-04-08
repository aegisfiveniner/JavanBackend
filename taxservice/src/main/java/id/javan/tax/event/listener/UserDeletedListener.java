package id.javan.tax.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import id.javan.tax.service.UserService;

@Component
public class UserDeletedListener {
  private static final Logger logger = LoggerFactory.getLogger(UserDeletedListener.class);

  @Autowired
  private UserService userService;
  
  @RabbitListener(queues = "taxservice.user.deleted")
  public void onMessageReceived(Long id) {
    logger.info("Received message from rmq : {}", id);

    try {
      userService.deleteUserById(id);
    } catch (Exception e) {
    }
  }
}
