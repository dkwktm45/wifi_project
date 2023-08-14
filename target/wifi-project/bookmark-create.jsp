<%@ page import="service.MainService" %>
<%@ page import="model.Bookmark" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<%
  int idx = request.getParameter("bookmark_id") == null ? -1 :Integer.parseInt(request.getParameter("bookmark_id"));
  MainService mainService = new MainService();
  Bookmark bookmark = null;
  if (idx > -1) {
    bookmark = mainService.getBookmark(idx);
  }
%>
<h1 class="text-primary">WIFI PROJECT</h1>
<h1 class="text-primary" ><%=bookmark == null ? "북마크 그룹 추가": "북마크 수정"%></h1>
<table class="mt-3">
  <tr>
    <td class="me-2">북마크 이름</td>
    <td id="index" style="display: none;"><%=bookmark == null ? "": bookmark.getBookmark_id()%></td>
    <td><input id="bookmark_name" placeholder="<%=bookmark == null ? "북마크 이름 작성": "북마크 이름 수정"%>"></td>
  </tr>
  <tr>
    <td class="me-2">순서</td>
    <td><input id="sequence" placeholder="ex) 1"></td>
  </tr>
</table>
<button type="button" class="btn btn-primary" onclick="insertBookmarkGroup()">추가!</button>
<script>
  function insertBookmarkGroup() {
    var bookmark_name= document.getElementById("bookmark_name").value;
    var sequence = document.getElementById("sequence").value;
    if (document.getElementById("bookmark_name").placeholder == "북마크 이름 작성") {
      location.href = "bookmark.jsp?bookmark_name=" + bookmark_name + "&sequence=" + sequence;
    } else {
      var index = document.getElementById("index").innerText;
      location.href = "bookmark.jsp?bookmark_id="+ index +"&bookmark_name=" + bookmark_name + "&sequence=" + sequence;
    }
  }
</script>
</body>
</html>
