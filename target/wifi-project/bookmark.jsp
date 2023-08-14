<%@ page import="service.MainService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Bookmark" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <title>bookmark</title>
</head>
<style>
  th{
    width: 100px;
  }
  thead{
    background-color: aquamarine;
  }
</style>
<body>
<%
  MainService mainService = new MainService();
  String bookmark_name = request.getParameter("bookmark_name") == null ? "" : request.getParameter("bookmark_name");
  int sequence = request.getParameter("sequence") == null ? -1 :Integer.parseInt(request.getParameter("sequence"));
  int index = request.getParameter("bookmark_id") == null ? -1 :Integer.parseInt(request.getParameter("bookmark_id"));
  String type = request.getParameter("type") == null ? "": request.getParameter("type");
  if (bookmark_name != "" && sequence != -1 && index == -1){
    mainService.insertBookmark(bookmark_name, sequence);
  }else if (bookmark_name != "" && sequence != -1 && index != -1){
    mainService.editBookmark(bookmark_name, sequence, index);
  } else if (type.equals("delete")&& index != -1) {
    mainService.deleteBookmark(index);
  }


  List<Bookmark> bookmarkList = mainService.getBookmarkGroup();
%>
<h1 class="text-primary">WIFI PROJECT</h1>
<div class="mb-2">
  <a type="button" class="btn btn-primary" href="index.jsp">홈</a>
  <a type="button" class="btn btn-primary" href="history.jsp">위치 히스토리 목록</a>
  <a type="button" class="btn btn-primary" href="wifiSuccess.jsp">와이파이 요청</a>
  <a type="button" class="btn btn-primary" href="bookmarkDetail.jsp">즐겨찾기 보기</a>
  <a type="button" class="btn btn-primary" href="bookmark.jsp">즐겨찾기 그룹 관리</a>
</div>
<button type="button" class="btn btn-primary my-2" onclick="goInsertBookmark()">북마크 그룹 이름 추가</button>
<table class="mt-2">
  <thead>
    <tr>
      <td>ID</td>
      <td>북마크 이름</td>
      <td>순서</td>
      <td>등록 일자</td>
      <td>수정 일자</td>
      <td>비고</td>
    </tr>
  </thead>
  <tbody>
  <%
    if (bookmarkList != null) {
      for (Bookmark bookmark : bookmarkList) {
  %>

    <tr>
      <td id="boomark_id"><%=bookmark.getBookmark_id()%></td>
      <td><%=bookmark.getBook_name()%></td>
      <td><%=bookmark.getSeq()%></td>
      <td><%=bookmark.getCreated_at()%></td>
      <td><%=bookmark.getUpdated_at() == null ? "" : bookmark.getUpdated_at()%></td>
      <td><button type="button" class="btn btn-primary opacity-75" onclick="editBookmark()">수정</button>
        <button type="button" class="btn btn-primary opacity-75" onclick="deleteBookmark()">삭제</button>
      </td>
    </tr>
  <%
      }
    }
  %>
  </tbody>
</table>
<script>
  function goInsertBookmark() {
    location.href = "bookmark-create.jsp"
  }

  function editBookmark() {
    var id = document.getElementById("boomark_id").innerText;
    location.href = "bookmark-create.jsp?bookmark_id=" + id;
  }

  function deleteBookmark() {
    var id = document.getElementById("boomark_id").innerText;
    location.href = "bookmark.jsp?bookmark_id=" + id + "&type=delete";
  }
</script>
</body>
</html>
