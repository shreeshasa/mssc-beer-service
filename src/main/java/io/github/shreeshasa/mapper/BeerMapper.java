package io.github.shreeshasa.mapper;

import io.github.shreeshasa.domain.Beer;
import io.github.shreeshasa.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * @author shreeshasa
 */
@DecoratedWith (BeerMapperDecorator.class)
@Mapper (uses = {DateMapper.class})
public interface BeerMapper {

  BeerDto beerToBeerDto(Beer beer);

  BeerDto beerToBeerDtoWithInventory(Beer beer);

  Beer beerDtoToBeer(BeerDto dto);
}
