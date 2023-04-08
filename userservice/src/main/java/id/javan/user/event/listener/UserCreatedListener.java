package id.javan.user.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import id.javan.user.entity.User;

@Component
public class UserCreatedListener {
  private static final Logger logger = LoggerFactory.getLogger(UserCreatedListener.class);

  @RabbitListener(queues = "userservice.user.created")
  public void onMessageReceived(User user) {
    logger.info("Received message from rmq : {}", user);
    System.out.println("Received message from rmq : " + user);
  }
}
