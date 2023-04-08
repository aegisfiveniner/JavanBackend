package id.javan.tax.event;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RmqConfig {
  @Bean 
  DirectExchange exchange() {
    return new DirectExchange("userservice", true, true);
  }

  @Bean
  Queue userServiceUserCreatedQueue() {
    return new Queue("taxservice.user.created", true);
  }

  @Bean
  Binding userServiceUserCreatedBinding(Queue userServiceUserCreatedQueue, DirectExchange exchange) {
    return BindingBuilder.bind(userServiceUserCreatedQueue).to(exchange).with("user.created");
  }

  @Bean
  Queue userServiceUserUpdatedQueue() {
    return new Queue("taxservice.user.updated", true);
  }

  @Bean
  Binding userServiceUserUpdatedBinding(Queue userServiceUserUpdatedQueue, DirectExchange exchange) {
    return BindingBuilder.bind(userServiceUserUpdatedQueue).to(exchange).with("user.updated");
  }

  @Bean
  Queue userServiceUserDeletedQueue() {
    return new Queue("taxservice.user.deleted", true);
  }

  @Bean
  Binding userServiceUserDeletedBinding(Queue userServiceUserDeletedQueue, DirectExchange exchange) {
    return BindingBuilder.bind(userServiceUserDeletedQueue).to(exchange).with("user.deleted");
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean 
  public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }
}
