package io.github.shreeshasa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author shreeshasa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto implements Serializable {

  private static final long serialVersionUID = 6678396828222871528L;

  private UUID id;
  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;
  private UUID beerId;
  private Integer quantityOnHand;
}
