package io.github.shreeshasa.listener;

import io.github.shreeshasa.model.event.BeerOrderDto;
import io.github.shreeshasa.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shreeshasa
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

  private final BeerRepository beerRepository;

  public Boolean validateOrder(BeerOrderDto beerOrderDto) {
    AtomicInteger beersNotFound = new AtomicInteger();
    beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
      if (beerRepository.findByUpc(beerOrderLineDto.getUpc()) == null) {
        beersNotFound.incrementAndGet();
      }
    });
    return beersNotFound.get() == 0;
  }
}
