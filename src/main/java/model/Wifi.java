package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Wifi {
  private String X_SWIFI_MGR_NO;
  private String X_SWIFI_WRDOFC;
  private float DISTANCE;
  private String X_SWIFI_MAIN_NM;
  private String X_SWIFI_ADRES1;
  private String X_SWIFI_ADRES2;
  private String X_SWIFI_INSTL_FLOOR;
  private String X_SWIFI_INSTL_TY;
  private String X_SWIFI_INSTL_MBY;
  private String X_SWIFI_SVC_SE;
  private String X_SWIFI_CMCWR;
  private String X_SWIFI_CNSTC_YEAR;
  private String X_SWIFI_INOUT_DOOR;
  private String X_SWIFI_REMARS3;
  private float LAT;
  private float LNT;
  private String WORK_DTTM;
}
