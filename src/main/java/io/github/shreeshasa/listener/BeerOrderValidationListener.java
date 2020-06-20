package io.github.shreeshasa.listener;

import io.github.shreeshasa.config.JmsConfig;
import io.github.shreeshasa.model.event.ValidateOrderRequest;
import io.github.shreeshasa.model.event.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author shreeshasa
 */
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

  private final BeerOrderValidator beerOrderValidator;
  private final JmsTemplate jmsTemplate;

  @JmsListener (destination = JmsConfig.VALIDATE_ORDER_QUEUE)
  private void listen(ValidateOrderRequest validateOrderRequest) {
    Boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrderDto());
    jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT_QUEUE, ValidateOrderResult.builder()
        .orderId(validateOrderRequest.getBeerOrderDto().getId())
        .isValid(isValid)
        .build());
  }
}
