package io.github.shreeshasa.repository;

import io.github.shreeshasa.domain.Beer;
import io.github.shreeshasa.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author shreeshasa
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {

  Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

  Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

  Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

  Beer findByUpc(String upc);
}