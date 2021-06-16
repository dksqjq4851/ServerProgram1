<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	
	</script>
	<style>
		table{
			width : 500px;
			border-collapse: collapse;
			
		}
		
		td{
			padding : 5px;
			border-top : 1px solid gray;
			border_bottom : 1px solid gray;
		}
	</style>
</head>
<body>
	<p>전체 게시글:  ${totalRecord}</p>
	<a href = "insertBoardPage.do">새글작성</a>
	<table>
		<thead>
			<tr>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan = "5">작성된게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var = "board" items="${list}">
					<tr>
						<td>${board.no}</td>
						<td>${board.author}</td>
						<td><a href= "selectBoardByNo.do?no=${board.no}">${board.title}</a></td>
						<td>${board.postdate}</td>
						<td>${board.hit}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>

		</tfoot>
	</table>
</body>
</html>