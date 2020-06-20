package io.github.shreeshasa.mapper;

import io.github.shreeshasa.domain.Beer;
import io.github.shreeshasa.model.BeerDto;
import io.github.shreeshasa.service.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author shreeshasa
 */
public abstract class BeerMapperDecorator implements BeerMapper {

  @Autowired
  private BeerInventoryService beerInventoryService;

  @Autowired
  @Qualifier ("delegate")
  private BeerMapper mapper;

  @Override
  public BeerDto beerToBeerDtoWithInventory(Beer beer) {
    BeerDto dto = mapper.beerToBeerDto(beer);
    dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
    return dto;
  }
}
