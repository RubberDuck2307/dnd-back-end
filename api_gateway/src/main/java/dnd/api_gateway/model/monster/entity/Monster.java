package dnd.api_gateway.model.monster.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Monster {

  private Long id;
  private Float Cr;
  private String name;
}
