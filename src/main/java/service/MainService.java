package service;


import lombok.RequiredArgsConstructor;
import model.Bookmark;
import model.History;
import model.MockResponse;
import model.Wifi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class MainService {

  private CommonService commonService = new CommonService();
  private DBConnection dbConnection = new DBConnection();
  private int size = 0;
  public int insetWifi(int start, int end) throws IOException {
    String url = String.format("http://openapi.seoul.go.kr:8088/4466435367646b7733344a72615161/json/TbPublicWifiInfo/%s/%s/",start , end);

    OkHttpClient client = new OkHttpClient();

    Request.Builder builder = new Request.Builder().url(url).get();
    Request req = builder.build();

    Response response = client.newCall(req).execute();

    if (response.isSuccessful()) {
      ResponseBody body = response.body();
      if (body != null){
        List<Wifi> list = commonService.getwifiList(body.string());
        dbConnection.listInset(list);
        size += list.size();
        if (list.size() == 1000) insetWifi(start + 1000 , end + 1000);
      }
    }
    return size;
  }

  public List<Wifi> getAround(float lat, float lng) {
    if (lat == 0 || lng == 0) {
      return null;
    }
    List<Wifi> wifis = dbConnection.getAround(lat,lng);
    return wifis;
  }

  public void insertHistory(float lat, float lng) {
    dbConnection.insertHistory_DB(lat,lng);
  }

  public List<History> getHistory(){
    return dbConnection.selectHistory();
  }

  public Wifi getWifi(String mgr_no){
    return dbConnection.getWifi(mgr_no);
  }

  public void insertBookmark(String bookmarkName, int sequence){
    dbConnection.insertBookmark(bookmarkName, sequence);
  }

  public List<Bookmark> getBookmarkGroup() {
    return dbConnection.groupList();
  }

  public Bookmark getBookmark(int idx) {
    return dbConnection.getBookmark(idx);
  }

  public void editBookmark(String bookmarkName, int sequence, int index) {
    dbConnection.editBookmarkDB(bookmarkName, sequence,index);
  }

  public void deleteBookmark(int index) {
    dbConnection.deleteBookmarkDB(index);
  }

  public List<Bookmark> getBookmarkList(String no){
    return dbConnection.selectBookmark(no);
  }

  public void insetWifiMark(String wifiMgr, int bookmarkId) {
    dbConnection.insertMarkDB(wifiMgr, bookmarkId);
  }

  public List<MockResponse> getWifiMarkList() {
    return dbConnection.WifiMarkListDB();
  }

  public void deleteMarkID(int mark_id) {
    dbConnection.deleteMarkId(mark_id);
  }

  public void deleteHistory(int idx) {
    dbConnection.deleteHistoryDB(idx);
  }
}
