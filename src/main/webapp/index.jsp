<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <!-- CSS -->
    <link rel="stylesheet" href="css/style.css?"/>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
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
        <form action="show-wifi.jsp" method="get">

            LAT: <input type="text" id="inputLat" name="inputLat">,
            LNT: <input type="text" id="inputLnt" name="inputLnt">
            <button type="button" id="getPositionButton">내 위치 가져오기</button>
            <button type="submit" id="nearWifi">근처WIPI정보 보기</button>

        </form>
    </section>
    <section>
        <table>
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
            <tr>
                <td colspan='17' align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
            </tr>

        </table>
    </section>
</main>


</body>
</html>