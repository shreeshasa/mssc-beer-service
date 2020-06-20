package io.github.shreeshasa.model.event;

import io.github.shreeshasa.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * @author shreeshasa
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }
}
