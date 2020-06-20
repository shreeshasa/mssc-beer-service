package io.github.shreeshasa.service;

import io.github.shreeshasa.model.BeerDto;
import io.github.shreeshasa.model.BeerPagedList;
import io.github.shreeshasa.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * @author shreeshasa
 */
public interface BeerService {

  BeerPagedList getBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

  BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

  BeerDto saveBeer(BeerDto beerDto);

  BeerDto updateBeer(UUID beerId, BeerDto beerDto);

  BeerDto getByUpc(String upc);
}
