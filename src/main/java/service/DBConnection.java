package service;

import model.Bookmark;
import model.History;
import model.MockResponse;
import model.Wifi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
  private CommonService commonService = new CommonService();

  public void listInset(List<Wifi> wifiList) {
    Connection connection = null;

    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "insert into wifi (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC," +
          " X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY," +
          " X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR," +
          " X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) values (?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?)";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      for (int i = 0; i < wifiList.size(); i++) {
        psmt.setString(1, wifiList.get(i).getX_SWIFI_MGR_NO());
        psmt.setString(2, wifiList.get(i).getX_SWIFI_WRDOFC());
        psmt.setString(3, wifiList.get(i).getX_SWIFI_MAIN_NM());
        psmt.setString(4, wifiList.get(i).getX_SWIFI_ADRES1());
        psmt.setString(5, wifiList.get(i).getX_SWIFI_ADRES2());
        psmt.setString(6, wifiList.get(i).getX_SWIFI_INSTL_FLOOR());
        psmt.setString(7, wifiList.get(i).getX_SWIFI_INSTL_TY());
        psmt.setString(8, wifiList.get(i).getX_SWIFI_INSTL_MBY());
        psmt.setString(9, wifiList.get(i).getX_SWIFI_SVC_SE());
        psmt.setString(10, wifiList.get(i).getX_SWIFI_CMCWR());
        psmt.setString(11, wifiList.get(i).getX_SWIFI_CNSTC_YEAR());
        psmt.setString(12, wifiList.get(i).getX_SWIFI_INOUT_DOOR());
        psmt.setString(13, wifiList.get(i).getX_SWIFI_REMARS3());
        psmt.setFloat(14, wifiList.get(i).getLAT());
        psmt.setFloat(15, wifiList.get(i).getLNT());
        psmt.setString(16, wifiList.get(i).getWORK_DTTM());

        psmt.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }

  public List<Wifi> getAround(float lat, float lng) {
    Connection connection = null;
    List<Wifi> wifis = new ArrayList<Wifi>();
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "SELECT *,((6371 *ACOS(COS(RADIANS(?)) *COS(RADIANS(wifi.LAT))*COS(radians(wifi.LNT)-RADIANS(?))+SIN(RADIANS(?))*SIN(RADIANS(LAT))))) as distance " +
          "FROM wifi " +
          "ORDER BY distance " +
          "LIMIT 20";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setFloat(1, lat);
      psmt.setFloat(2, lng);
      psmt.setFloat(3, lat);
      rs = psmt.executeQuery();

      while (rs.next()) {
        Wifi wifi = new Wifi(
            rs.getString("X_SWIFI_MGR_NO"), rs.getString("X_SWIFI_WRDOFC"), rs.getFloat("DISTANCE"), rs.getString("X_SWIFI_MAIN_NM"),
            rs.getString("X_SWIFI_ADRES1"), rs.getString("X_SWIFI_ADRES2"), rs.getString("X_SWIFI_INSTL_FLOOR"), rs.getString("X_SWIFI_INSTL_TY"),
            rs.getString("X_SWIFI_INSTL_MBY"), rs.getString("X_SWIFI_SVC_SE"), rs.getString("X_SWIFI_CMCWR"), rs.getString("X_SWIFI_CNSTC_YEAR"), rs.getString("X_SWIFI_INOUT_DOOR"),
            rs.getString("X_SWIFI_REMARS3"), rs.getFloat("LAT"), rs.getFloat("LNT"), rs.getString("WORK_DTTM")
        );
        wifis.add(wifi);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
    return wifis;
  }

  public void insertHistory_DB(float lat, float lng) {
    Connection connection = null;
    List<Wifi> wifis = new ArrayList<Wifi>();
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "insert into history (lat , lnt, search_dt) values (?, ? , ?);";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setFloat(1, lat);
      psmt.setFloat(2, lng);
      psmt.setString(3,commonService.getTime());
      psmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }

  public List<History> selectHistory() {
    Connection connection = null;
    List<History> histories = new ArrayList<>();
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "select * from history;";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      rs = psmt.executeQuery();

      while (rs.next()) {
        History history = History.builder().history_id(rs.getInt(1))
            .lat(rs.getFloat(2))
            .lnt(rs.getFloat(3))
            .search_dt(rs.getString(4)).build();
        histories.add(history);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
    return histories;
  }

  public Wifi getWifi(String mgrNo) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "select * from wifi where X_SWIFI_MGR_NO = ?;";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setString(1, mgrNo);
      rs = psmt.executeQuery();

      return new Wifi(
          rs.getString("X_SWIFI_MGR_NO"), rs.getString("X_SWIFI_WRDOFC"), 0.00F, rs.getString("X_SWIFI_MAIN_NM"),
          rs.getString("X_SWIFI_ADRES1"), rs.getString("X_SWIFI_ADRES2"), rs.getString("X_SWIFI_INSTL_FLOOR"), rs.getString("X_SWIFI_INSTL_TY"),
          rs.getString("X_SWIFI_INSTL_MBY"), rs.getString("X_SWIFI_SVC_SE"), rs.getString("X_SWIFI_CMCWR"), rs.getString("X_SWIFI_CNSTC_YEAR"), rs.getString("X_SWIFI_INOUT_DOOR"),
          rs.getString("X_SWIFI_REMARS3"), rs.getFloat("LAT"), rs.getFloat("LNT"), rs.getString("WORK_DTTM")
      );

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
    return null;
  }

  public void insertBookmark(String bookmarkName, int sequence) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "insert into bookmark (book_name,seq,created_at) values (?, ? ,?)";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setString(1, bookmarkName);
      psmt.setInt(2, sequence);
      psmt.setString(3, commonService.getTime());
      psmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }

  public List<Bookmark> groupList() {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "select * from bookmark order by seq";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      rs = psmt.executeQuery();
      List<Bookmark> bookmarks = new ArrayList<>();

      while (rs.next()) {
        bookmarks.add(
            Bookmark.builder().bookmark_id(rs.getInt(1))
                .book_name(rs.getString(2))
                .seq(rs.getInt(3))
                .created_at(rs.getString(4))
                .updated_at(rs.getString(5))
                .build()
        );
      }
      return bookmarks;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
    return null;
  }

  public Bookmark getBookmark(int idx) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "select * from bookmark where bookmark_id = ?";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setInt(1, idx);
      rs = psmt.executeQuery();

      return Bookmark.builder().bookmark_id(rs.getInt(1))
          .book_name(rs.getString(2)).seq(rs.getInt(3)).build();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
    return null;
  }

  public void editBookmarkDB(String bookmarkName, int sequence, int index) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "update bookmark set book_name = ?, seq = ? , updated_at = ? where bookmark_id = ?";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setString(1, bookmarkName);
      psmt.setInt(2 ,sequence);
      psmt.setString(3 ,commonService.getTime());
      psmt.setInt(4, index);
      psmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }

  public void deleteBookmarkDB(int index) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "delete from bookmark where bookmark_id = ?";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setInt(1, index);
      psmt.executeUpdate();

      sql = "delete from wifiMark where bookmark_id = ?";
      psmt = connection.prepareStatement(sql);
      psmt.setInt(1 , index);
      psmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }

  public List<Bookmark> selectBookmark(String no) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "select * from bookmark " +
          "WHERE bookmark_id not in " +
          "(SELECT wm.bookmark_id from wifiMark wm WHERE wm.X_SWIFI_MGR_NO = ?)";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setString(1, no);
      rs = psmt.executeQuery();
      List<Bookmark> bookmarkList = new ArrayList<>();
      while (rs.next()){
        bookmarkList.add(Bookmark.builder()
            .bookmark_id(rs.getInt(1))
            .book_name(rs.getString(2))
            .build());
      }
      return bookmarkList;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }

    return null;
  }

  public void insertMarkDB(String wifiMgr, int bookmarkId) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "insert into wifiMark (bookmark_id, X_SWIFI_MGR_NO , created_at) values (?, ?,?)";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setInt(1, bookmarkId);
      psmt.setString(2,wifiMgr);
      psmt.setString(3,commonService.getTime());
      psmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }

  public List<MockResponse> WifiMarkListDB() {

    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "SELECT wm.mark_id ,b.book_name,w.X_SWIFI_MGR_NO ,w.X_SWIFI_MAIN_NM, wm.created_at  from wifiMark wm " +
          "join bookmark b on wm.bookmark_id = b.bookmark_id " +
          "JOIN wifi w ON w.X_SWIFI_MGR_NO = wm.X_SWIFI_MGR_NO";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      rs = psmt.executeQuery();

      List<MockResponse> list= new ArrayList<>();
      while (rs.next()){
        list.add(MockResponse.builder()
            .mark_id(rs.getInt(1)).bookmark_name(rs.getString(2))
            .X_SWIFI_MGR_NO(rs.getString(3)).X_SWIFI_MAIN_NM(rs.getString(4))
            .reg_dt(rs.getString(5)).build());
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
    return null;
  }

  public void deleteMarkId(int markId) {

    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "delete from wifiMark where mark_id = ?";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setInt(1 , markId);
      psmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }

  public void deleteHistoryDB(int idx) {
    Connection connection = null;
    try {
      // SQLite JDBC 체크
      Class.forName("org.sqlite.JDBC");

      // SQLite 데이터베이스 파일에 연결
      String dbFile = "C:\\Users\\user\\Desktop\\zerobase base\\과제\\wifi-project\\wifiDB.db";
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      ResultSet rs = null;
      // SQL 수행
      Statement stat = connection.createStatement();
      String sql = "delete from history where history_id = ?";
      PreparedStatement psmt = null;
      psmt = connection.prepareStatement(sql);
      psmt.setInt(1 , idx);
      psmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception e) {
        }
      }
    }
  }
}
