package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class History {
  private Integer history_id;
  private float lat;
  private float lnt;
  private String search_dt;
}
