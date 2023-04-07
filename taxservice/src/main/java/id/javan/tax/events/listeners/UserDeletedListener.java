package id.javan.tax.events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import id.javan.tax.entity.User;

@Component
public class UserDeletedListener {
  private static final Logger logger = LoggerFactory.getLogger(UserDeletedListener.class);

  @RabbitListener(queues = "userservice.user.deleted")
  public void onMessageReceived(Long id) {
    logger.info("Received message from rmq : {}", id);
    System.out.println("Received message from rmq : " + id);
  }
}
