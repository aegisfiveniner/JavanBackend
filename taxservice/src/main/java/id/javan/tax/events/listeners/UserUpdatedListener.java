package id.javan.tax.events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import id.javan.tax.entity.User;

@Component
public class UserUpdatedListener {
  private static final Logger logger = LoggerFactory.getLogger(UserUpdatedListener.class);

  @RabbitListener(queues = "userservice.user.updated")
  public void onMessageReceived(User user) {
    logger.info("Received message from rmq : {}", user);
    System.out.println("Received message from rmq : " + user);
  }
}
