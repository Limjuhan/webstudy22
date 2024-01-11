<%@ page import="com.example.zerobasestudy22.WifiInfoService" %>
<%@ page import="com.example.zerobasestudy22.WifiInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.zerobasestudy22.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <!-- CSS -->
    <link rel="stylesheet" href="css/style.css?after"/>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
<%
    String lat = request.getParameter("inputLat");
    String lnt = request.getParameter("inputLnt");


    WifiInfoService service = new WifiInfoService();
    HistoryService historyService = new HistoryService();
    historyService.insertHistory(lat, lnt);


    List<WifiInfo> wifiList = service.getList(lat, lnt);


%>
<h1>와이파이 정보 구하기</h1>
<br/>
<!-- header-->
<header class="header">
<nav>
    <ul class="header_menu">
        <li><a href="index.jsp">홈</a></li>
        <li>|</li>
        <li><a href="history.jsp">위치 히스토리 목록</a></li>
        <li>|</li>
        <li><a href="#" onclick="location.href='load-wifi.jsp'">OpenAPI와이파이정보가져오기</a></li>
    </ul>
</nav>
</header>
<main>
    <section>
        LAT:<input type="text" id="inputLat">,
        LNT:<input type="text" id="inputLnt">
        <button id="getPositionButton">내 위치 가져오기</button>
        <button>근처WIPI정보 보기</button>
    </section>
    <section>
        <table>
            <thead>
            <tr>
            <th>거리</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (WifiInfo list: wifiList) {
                    out.write("<tr>");
                    out.write("<td>" + list.getDistance() + "</td>");
                    out.write("<td>" + list.getMgr_no() + "</td>");
                    out.write("<td>" + list.getWrdofc() + "</td>");
                    out.write("<td>" + list.getMain_nm() + "</td>");
                    out.write("<td>" + list.getAdres1() + "</td>");
                    out.write("<td>" + list.getAdres2() + "</td>");
                    out.write("<td>" + list.getInstl_ty() + "</td>");
                    out.write("<td>" + "</td>");
                    out.write("<td>" + list.getInstl_mby() + "</td>");
                    out.write("<td>" + list.getSvc_se() + "</td>");
                    out.write("<td>" + list.getCmcwr() + "</td>");
                    out.write("<td>" + list.getCnstc_year() + "</td>");
                    out.write("<td>" + list.getInout_door() + "</td>");
                    out.write("<td>"  + "</td>");
                    out.write("<td>" + list.getLat() + "</td>");
                    out.write("<td>" + list.getLnt() + "</td>");
                    out.write("<td>" + list.getWork_dttm() + "</td>");
                    out.write("</tr>");
                }%>
            </tbody>
        </table>
    </section>
</main>


</body>
</html>