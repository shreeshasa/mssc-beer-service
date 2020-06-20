package io.github.shreeshasa.service;

import java.util.UUID;

/**
 * @author shreeshasa
 */
public interface BeerInventoryService {

  Integer getOnHandInventory(UUID beerId);
}
