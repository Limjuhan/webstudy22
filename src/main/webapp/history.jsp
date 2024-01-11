<%@ page import="com.example.zerobasestudy22.HistoryService" %>
<%@ page import="com.example.zerobasestudy22.HistoryInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="inc_menu.jsp"/>
	<link rel="stylesheet" href="css/style.css"/>
</head>
<%
	HistoryService historyService = new HistoryService();

	List<HistoryInfo> historyInfoList= historyService.selectHistory();


%>
<body>
<section>
	<table>
		<tr>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</tr>
		<tr >
			<% for (HistoryInfo list: historyInfoList) {
				out.write("<tr>");
				out.write("<td>" + list.getID() + "</td>");
				out.write("<td>" + list.getX() + "</td>");
				out.write("<td>" + list.getY() + "</td>");
				out.write("<td>" + list.getCheckDate() + "</td>");
				out.write("<td>" + "<button id='removeHistory'>삭제</button>"+"</td>");
				out.write("</tr>");
			}%>
		</tr>

	</table>
</section>
</body>
</html>