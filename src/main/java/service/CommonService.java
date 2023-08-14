package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import model.Wifi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class CommonService {
  public CommonService() {
  }

  public List<Wifi> getwifiList(String strData){
    JsonParser parser = new JsonParser();
    JsonObject jsonObject = (JsonObject) parser.parse(strData);
    String objString = jsonObject.get("TbPublicWifiInfo").getAsJsonObject().get("row").toString();

    Gson gson = new Gson();
    List<Wifi> list = gson.fromJson(objString , new TypeToken<List<Wifi>>(){}.getType());
    return list;
  }
  public String getTime() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    return sdf.format(timestamp).toString();
  }

}
