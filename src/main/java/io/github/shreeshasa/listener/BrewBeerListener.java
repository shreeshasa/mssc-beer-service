package io.github.shreeshasa.listener;

import io.github.shreeshasa.config.JmsConfig;
import io.github.shreeshasa.domain.Beer;
import io.github.shreeshasa.model.event.BrewBeerEvent;
import io.github.shreeshasa.model.event.NewInventoryEvent;
import io.github.shreeshasa.model.BeerDto;
import io.github.shreeshasa.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shreeshasa
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BrewBeerListener {

  private final BeerRepository beerRepository;
  private final JmsTemplate jmsTemplate;

  @Transactional
  @JmsListener (destination = JmsConfig.BREWING_REQUEST_QUEUE)
  public void listen(BrewBeerEvent event) {
    BeerDto beerDto = event.getBeerDto();

    Beer beer = beerRepository.getOne(beerDto.getId());
    beerDto.setQuantityOnHand(beer.getQuantityToBrew());

    NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

    log.debug("Brewed beer: {}, Quantity On Hand: {}", beer.getMinOnHand(), beerDto.getQuantityOnHand());

    jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
  }
}
