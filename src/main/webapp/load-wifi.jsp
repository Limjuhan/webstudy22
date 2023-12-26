<%@ page import="com.example.zerobasestudy22.GetWifi" %>
<%@ page import="org.mariadb.jdbc.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: starl
  Date: 2023-12-25
  Time: 오후 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        body {text-align: center;}
    </style>
</head>
<body>
<%
    GetWifi g = new GetWifi();
    int total = g.getCount();
    g.getData();

%>

<h1 ><%=total%>개의 WIFI정보를 정상적으로 저장하였습니다.</h1>
<a href="#" onclick="location.href='index.jsp'">홈으로가기</a>
</body>
</html>
