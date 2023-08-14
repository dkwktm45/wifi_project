package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MockResponse {

  private Integer mark_id;
  private Integer bookMarkID;
  private String bookmark_name;
  private String X_SWIFI_MGR_NO;
  private String X_SWIFI_MAIN_NM;
  private String reg_dt;
}
