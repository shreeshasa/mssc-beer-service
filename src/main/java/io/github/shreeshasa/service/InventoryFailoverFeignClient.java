package io.github.shreeshasa.service;

import io.github.shreeshasa.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author shreeshasa
 */
@FeignClient (name = "inventory-failover-service")
public interface InventoryFailoverFeignClient {

  @GetMapping (value = "/inventory-failover")
  ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();
}
