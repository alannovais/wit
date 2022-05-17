package com.challenger.wit.consumer;

import com.challenger.wit.manager.CalculatorManager;
import com.challenger.wit.service.RabbitMQSendMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.RabbitMQConstants;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.InputsCalculator;

@Component
public class CalculatorRabbitMQConsumer {
    @Autowired
    private CalculatorManager calculatorManager;

    @Autowired
    private RabbitMQSendMessage rabbitMQSendMessage;

	@RabbitListener(queues = RabbitMQConstants.SUM_QUEUE)
    private void consumerSum(String mensagem) throws JsonProcessingException, InterruptedException {
      InputsCalculator inputsCalculator = new ObjectMapper().readValue(mensagem, InputsCalculator.class);
      this.rabbitMQSendMessage.sendMessage(RabbitMQConstants.RESULT_SUM_QUEUE,
          this.calculatorManager.sum(inputsCalculator.a, inputsCalculator.b));
    }
	
	@RabbitListener(queues = RabbitMQConstants.SUB_QUEUE)
    private void consumerSub(String mensagem) throws JsonProcessingException, InterruptedException {
      InputsCalculator inputsCalculator = new ObjectMapper().readValue(mensagem, InputsCalculator.class);
      this.rabbitMQSendMessage.sendMessage(RabbitMQConstants.RESULT_SUB_QUEUE,
          this.calculatorManager.sub(inputsCalculator.a, inputsCalculator.b));
    }
	
	@RabbitListener(queues = RabbitMQConstants.MULT_QUEUE)
    private void consumerMult(String mensagem) throws JsonProcessingException, InterruptedException {
      InputsCalculator inputsCalculator = new ObjectMapper().readValue(mensagem, InputsCalculator.class);
      this.rabbitMQSendMessage.sendMessage(RabbitMQConstants.RESULT_MULT_QUEUE,
          this.calculatorManager.mult(inputsCalculator.a, inputsCalculator.b));
    }
	
	@RabbitListener(queues = RabbitMQConstants.DIV_QUEUE)
    private void consumerDiv(String mensagem) throws JsonProcessingException, InterruptedException {
      InputsCalculator inputsCalculator = new ObjectMapper().readValue(mensagem, InputsCalculator.class);
      this.rabbitMQSendMessage.sendMessage(RabbitMQConstants.RESULT_DIV_QUEUE,
          this.calculatorManager.div(inputsCalculator.a, inputsCalculator.b));
    }
}
