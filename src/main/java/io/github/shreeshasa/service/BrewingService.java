package io.github.shreeshasa.service;

import io.github.shreeshasa.config.JmsConfig;
import io.github.shreeshasa.domain.Beer;
import io.github.shreeshasa.model.event.BrewBeerEvent;
import io.github.shreeshasa.mapper.BeerMapper;
import io.github.shreeshasa.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shreeshasa
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {

  private final BeerRepository beerRepository;
  private final BeerInventoryService beerInventoryService;
  private final JmsTemplate jmsTemplate;
  private final BeerMapper beerMapper;

  @Scheduled (fixedRate = 5000) //every 5 seconds
  public void checkForLowInventory() {
    List<Beer> beers = beerRepository.findAll();
    beers.forEach(beer -> {
      Integer quantityOnHand = beerInventoryService.getOnHandInventory(beer.getId());
      log.debug("Min On Hand is: {}", beer.getMinOnHand());
      log.debug("Inventory is: {}", quantityOnHand);
      if (beer.getMinOnHand() >= quantityOnHand) {
        jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
      }
    });
  }
}
