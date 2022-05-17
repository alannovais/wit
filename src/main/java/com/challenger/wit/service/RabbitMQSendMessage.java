package com.challenger.wit.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSendMessage {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
  
    @Autowired
    private ObjectMapper objectMapper;
  
    public void sendMessage(String queueName, Object menssage){
      try {
        String menssageJson = this.objectMapper.writeValueAsString(menssage);
        this.rabbitTemplate.convertAndSend(queueName, menssageJson);
      } catch (Exception e){
        e.printStackTrace();
      }
    }
}
