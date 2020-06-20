package io.github.shreeshasa.service;

import io.github.shreeshasa.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author shreeshasa
 */
@Slf4j
@RequiredArgsConstructor
@Profile ("local-discovery")
@Service
public class BeerInventoryServiceFeign implements BeerInventoryService {

  private final InventoryServiceFeignClient inventoryServiceFeignClient;

  @Override
  public Integer getOnHandInventory(UUID beerId) {
    log.debug("Calling Inventory Service - BeerId: {}", beerId);
    ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(beerId);
    Integer inventoryOnHand = Objects.requireNonNull(responseEntity.getBody())
        .stream()
        .mapToInt(BeerInventoryDto::getQuantityOnHand)
        .sum();
    log.debug("BeerId: {} On Hand is: {}", beerId, inventoryOnHand);
    return inventoryOnHand;
  }
}
