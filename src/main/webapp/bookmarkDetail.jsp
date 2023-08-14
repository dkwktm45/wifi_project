<%@ page import="service.MainService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.MockResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <title>bookmarkDetail</title>
</head>
<style>
    th{
        width: 100px;
    }
    input{
        width: 200px;
        height: 40px;
    }
    thead{
        background-color: aquamarine;
    }
</style>
<body>
<%
    MainService mainService = new MainService();
    int bookmark_id = request.getParameter("bookmark_id") == null ? -1: Integer.parseInt(request.getParameter("bookmark_id"));
    String wifi_mgr = request.getParameter("mgr");
    String mark_id = request.getParameter("mark_id");

    if (bookmark_id != -1 && wifi_mgr != null) {
      mainService.insetWifiMark(wifi_mgr,bookmark_id);
    }

    if (mark_id != null) {
      mainService.deleteMarkID(Integer.parseInt(mark_id));
    }
    List<MockResponse> wifiMarklist = mainService.getWifiMarkList();
%>
<h1 class="text-primary">WIFI PROJECT</h1>
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
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이 명</th>
        <th>등록 일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (wifiMarklist != null && !wifiMarklist.isEmpty()) {
          for (MockResponse mockResponse : wifiMarklist){
    %>
        <tr>
            <td><%=mockResponse.getMark_id()%></td>
            <td><%=mockResponse.getBookmark_name()%></td>
            <td><a href="wifiDetail.jsp?X_SWIFI_MGR_NO=<%=mockResponse.getX_SWIFI_MGR_NO()%>"><%=mockResponse.getX_SWIFI_MAIN_NM()%></a></td>
            <td><%=mockResponse.getReg_dt()%></td>
            <td><button type="button" class="btn btn-primary opacity-75" onclick="deleteMark(<%=mockResponse.getMark_id()%>)">삭제</button></td>
        </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<script>
    function deleteMark(index) {
        location.href = "bookmarkDetail.jsp?mark_id=" + index;
    }
</script>
</body>
</html>
