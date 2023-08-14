<%@ page import="service.MainService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.History" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>history</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
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
<h1 class="text-primary">WIFI PROJECT</h1>
<%
  MainService mainService = new MainService();
  String id = request.getParameter("history_id");

  if (id != null) {
    mainService.deleteHistory(Integer.parseInt(id));
  }

  List<History> historyList = mainService.getHistory();
%>
<div class="mb-2">
  <a type="button" class="btn btn-primary" href="index.jsp">홈</a>
  <a type="button" class="btn btn-primary" href="history.jsp">위치 히스토리 목록</a>
  <a type="button" class="btn btn-primary" href="wifiSuccess.jsp">와이파이 요청</a>
  <a type="button" class="btn btn-primary" href="bookmarkDetail.jsp">즐겨찾기 보기</a>
  <a type="button" class="btn btn-primary" href="bookmark.jsp">즐겨찾기 그룹 관리</a>
</div>
<table class="mt-2">
  <thead>
    <tr>
      <td>ID</td>
      <td>X좌표</td>
      <td>Y좌표</td>
      <td>조회일자</td>
      <td>비고</td>
    </tr>
  </thead>
  <tbody>
  <%
    if (!historyList.isEmpty()) {
      for (History history : historyList) {
  %>
  <tr>
    <td><%=history.getHistory_id()%></td>
    <td><%=history.getLat()%></td>
    <td><%=history.getLnt()%></td>
    <td><%=history.getSearch_dt()%></td>
    <td><button type="button" class="btn btn-primary opacity-75"  onclick="deleteHistory(<%=history.getHistory_id()%>)">삭제</button></td>
  </tr>
  <%
      }
    }
  %>
  </tbody>
</table>
</body>
</html>
<script>
    function deleteHistory(id) {
        location.href = "history.jsp?history_id=" + id;
    }
</script>