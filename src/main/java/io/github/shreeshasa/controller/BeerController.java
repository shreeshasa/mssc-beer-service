package io.github.shreeshasa.controller;

import io.github.shreeshasa.model.BeerDto;
import io.github.shreeshasa.model.BeerPagedList;
import io.github.shreeshasa.model.BeerStyleEnum;
import io.github.shreeshasa.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author shreeshasa
 */
@RestController
@RequestMapping ("/api/v1")
@RequiredArgsConstructor
public class BeerController {

  private static final Integer DEFAULT_PAGE_NUMBER = 0;
  private static final Integer DEFAULT_PAGE_SIZE = 25;

  private final BeerService beerService;

  @GetMapping (path = "/beer", produces = {"application/json"})
  public ResponseEntity<BeerPagedList> getBeers(@RequestParam (value = "pageNumber", required = false) Integer pageNumber,
                                                @RequestParam (value = "pageSize", required = false) Integer pageSize,
                                                @RequestParam (value = "beerName", required = false) String beerName,
                                                @RequestParam (value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                @RequestParam (value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
    if (showInventoryOnHand == null) {
      showInventoryOnHand = false;
    }

    if (pageNumber == null || pageNumber < 0) {
      pageNumber = DEFAULT_PAGE_NUMBER;
    }

    if (pageSize == null || pageSize < 1) {
      pageSize = DEFAULT_PAGE_SIZE;
    }

    BeerPagedList beerList = beerService.getBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
    return new ResponseEntity<>(beerList, HttpStatus.OK);
  }

  @GetMapping ("/beer/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable ("beerId") UUID beerId,
                                             @RequestParam (value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
    if (showInventoryOnHand == null) {
      showInventoryOnHand = false;
    }
    return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
  }

  @GetMapping ("/beerUpc/{upc}")
  public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable ("upc") String upc) {
    return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
  }

  @PostMapping ("/beer")
  public ResponseEntity saveBeer(@Validated @RequestBody BeerDto beerDto) {
    return new ResponseEntity<>(beerService.saveBeer(beerDto), HttpStatus.CREATED);
  }

  @PutMapping ("/beer/{beerId}")
  public ResponseEntity updateBeer(@PathVariable ("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) {
    return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
  }
}
