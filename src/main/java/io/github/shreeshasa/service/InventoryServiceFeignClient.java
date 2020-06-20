package io.github.shreeshasa.service;

import io.github.shreeshasa.config.FeignClientConfig;
import io.github.shreeshasa.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

/**
 * @author shreeshasa
 */
@FeignClient (name = "inventory-service", fallback = InventoryFailoverServiceFeignClient.class, configuration = FeignClientConfig.class)
public interface InventoryServiceFeignClient {

  @GetMapping (value = BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
  ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
