package com.dead.queue.deadqueuepattern;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Component
@RestController
public class DeadLetterSendReceive {

  @Autowired
  private RabbitTemplate outgoingSender;

  @GetMapping("/send")
  public void sender() {
    ExampleObject ex = new ExampleObject();
    System.out.println("Sending :" + ex.getDate() + "At :" + LocalDateTime.now());
    outgoingSender.convertAndSend(ex);
  }

  // Annotation to listen for an ExampleObject
  @RabbitListener(queues = MQConfig.INCOMING_QUEUE)
  public void handleMessage(ExampleObject exampleObject) {
    System.out.println("Received " + exampleObject.getDate()+ " At: "+LocalDateTime.now());
  }

}
