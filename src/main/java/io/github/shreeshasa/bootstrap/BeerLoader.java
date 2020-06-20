package io.github.shreeshasa.bootstrap;

import io.github.shreeshasa.domain.Beer;
import io.github.shreeshasa.model.BeerStyleEnum;
import io.github.shreeshasa.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author shreeshasa
 */
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

  private static final String BEER_1_UPC = "0631234200036";
  private static final String BEER_2_UPC = "0631234300019";
  private static final String BEER_3_UPC = "0083783375213";

  private final BeerRepository beerRepository;

  @Override
  public void run(String... args) {
    if (beerRepository.count() == 0) {
      loadBeerObjects();
    }
  }

  private void loadBeerObjects() {
    Beer b1 = Beer.builder()
        .beerName("Mango Bobs")
        .beerStyle(BeerStyleEnum.IPA.name())
        .minOnHand(12)
        .quantityToBrew(200)
        .price(new BigDecimal("12.95"))
        .upc(BEER_1_UPC)
        .build();
    beerRepository.save(b1);

    Beer b2 = Beer.builder()
        .beerName("Galaxy Cat")
        .beerStyle(BeerStyleEnum.PALE_ALE.name())
        .minOnHand(12)
        .quantityToBrew(200)
        .price(new BigDecimal("12.95"))
        .upc(BEER_2_UPC)
        .build();
    beerRepository.save(b2);

    Beer b3 = Beer.builder()
        .beerName("Pinball Porter")
        .beerStyle(BeerStyleEnum.PORTER.name())
        .minOnHand(12)
        .quantityToBrew(200)
        .price(new BigDecimal("12.95"))
        .upc(BEER_3_UPC)
        .build();
    beerRepository.save(b3);
  }
}
