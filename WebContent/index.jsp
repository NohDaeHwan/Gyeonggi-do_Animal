<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.jsp.animal.domain.user.User"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>경기 동물 지킴이</title>
</head>
<body>
	<%
		RequestDispatcher dis = request.getRequestDispatcher("search?cmd=index");
		dis.forward(request, response);
	%>
</body>
</html>