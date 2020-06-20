package io.github.shreeshasa.service;

import io.github.shreeshasa.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author shreeshasa
 */
@RequiredArgsConstructor
@Component
public class InventoryFailoverServiceFeignClient implements InventoryServiceFeignClient {

  private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

  @Override
  public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
    return inventoryFailoverFeignClient.getOnHandInventory();
  }
}
