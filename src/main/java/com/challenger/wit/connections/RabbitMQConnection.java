package com.challenger.wit.connections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import constants.RabbitMQConstants;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

  private AmqpAdmin amqpAdmin;

  public RabbitMQConnection(AmqpAdmin amqpAdmin){
    this.amqpAdmin = amqpAdmin;
  }

  private Queue sumQueue(){
    return new Queue(RabbitMQConstants.RESULT_SUM_QUEUE, true, false, false);
  }

  private Queue subQueue(){
    return new Queue(RabbitMQConstants.RESULT_SUB_QUEUE, true, false, false);
  }

  private Queue multiQueue(){
    return new Queue(RabbitMQConstants.RESULT_MULT_QUEUE, true, false, false);
  }

  private Queue divQueue(){
    return new Queue(RabbitMQConstants.RESULT_DIV_QUEUE, true, false, false);
  }

  private DirectExchange directExchangeSum() {
    return new DirectExchange(RabbitMQConstants.RESULT_SUM_EXCHANGE);
  }

  private DirectExchange directExchangeSub() {
    return new DirectExchange(RabbitMQConstants.RESULT_SUB_EXCHANGE);
  }

  private DirectExchange directExchangeMult() {
    return new DirectExchange(RabbitMQConstants.RESULT_MULT_EXCHANGE);
  }

  private DirectExchange directExchangeDiv() {
    return new DirectExchange(RabbitMQConstants.RESULT_DIV_EXCHANGE);
  }

  private Binding bindingSum(){
    return new Binding(RabbitMQConstants.RESULT_SUM_QUEUE, Binding.DestinationType.QUEUE, RabbitMQConstants.RESULT_SUM_EXCHANGE, RabbitMQConstants.RESULT_SUM_QUEUE, null);
  }

  private Binding bindingSub(){
    return new Binding(RabbitMQConstants.RESULT_SUB_QUEUE, Binding.DestinationType.QUEUE, RabbitMQConstants.RESULT_SUB_EXCHANGE, RabbitMQConstants.RESULT_SUB_QUEUE, null);
  }

  private Binding bindingMult(){
    return new Binding(RabbitMQConstants.RESULT_MULT_QUEUE, Binding.DestinationType.QUEUE, RabbitMQConstants.RESULT_MULT_EXCHANGE, RabbitMQConstants.RESULT_MULT_QUEUE, null);
  }

  private Binding bindingDiv(){
    return new Binding(RabbitMQConstants.RESULT_DIV_QUEUE, Binding.DestinationType.QUEUE, RabbitMQConstants.RESULT_DIV_EXCHANGE, RabbitMQConstants.RESULT_DIV_QUEUE, null);
  }

  @PostConstruct
  private void adding(){
    Queue sumQueue = this.sumQueue();
    Queue subQueue = this.subQueue();
    Queue multQueue = this.multiQueue();
    Queue divQueue = this.divQueue();

    DirectExchange sumExchange = this.directExchangeSum();
    DirectExchange subExchange = this.directExchangeSub();
    DirectExchange multExchange = this.directExchangeMult();
    DirectExchange divExchange = this.directExchangeDiv();

    Binding sumBinding = this.bindingSum();
    Binding subBinding = this.bindingSub();
    Binding multBinding = this.bindingMult();
    Binding divBinding = this.bindingDiv();

    this.amqpAdmin.declareQueue(sumQueue);
    this.amqpAdmin.declareQueue(subQueue);
    this.amqpAdmin.declareQueue(multQueue);
    this.amqpAdmin.declareQueue(divQueue);

    this.amqpAdmin.declareExchange(sumExchange);
    this.amqpAdmin.declareExchange(subExchange);
    this.amqpAdmin.declareExchange(multExchange);
    this.amqpAdmin.declareExchange(divExchange);

    this.amqpAdmin.declareBinding(sumBinding);
    this.amqpAdmin.declareBinding(subBinding);
    this.amqpAdmin.declareBinding(multBinding);
    this.amqpAdmin.declareBinding(divBinding);
  }
}