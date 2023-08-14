package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Bookmark {
  private Integer bookmark_id;
  private String x_swifi_mgr_no;
  private String book_name;
  private int seq;
  private String created_at;
  private String updated_at;
}
