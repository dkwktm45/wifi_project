<%@ page import="service.MainService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>wifiSuccess</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<%
  MainService mainService = new MainService();
%>
<h1 class="text-primary">WIFI PROJECT</h1>
<div><a type="button" class="btn btn-primary" href="index.jsp">돌아가기</a></div>
<h1><%=mainService.insetWifi(1, 1000)%>개의 데이터를 성공적으로 가져오셨습니다.</h1>
</body>
</html>
