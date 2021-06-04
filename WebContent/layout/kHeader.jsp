<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
        <!-- Favicon-->
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <!-- Navigation-->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container px-5">
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">경기 동물 지킴이</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/about.jsp">소개</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">메뉴</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/search?cmd=animalhosptl&page=0">동물 병원</a></li>
                                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=0">동물 약국</a></li>
                                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/search?cmd=animalfacilit&page=0">유기 동물 보호 시설</a></li>
                                    
                                </ul>
                            </li>
                            <c:choose>
          						<c:when test="${sessionScope.User != null}">
          						<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/user?cmd=logout">로그아웃</a></li>
          						<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/journal?cmd=list&page=0&userId=${sessionScope.User.id}">마이 페이지</a></li>
          						</c:when>
          						<c:otherwise>
          							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/user?cmd=loginForm">로그인</a></li>
          							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/user?cmd=joinForm">회원가입</a></li>
          						</c:otherwise>
          					</c:choose> 
                        </ul>
                    </div>
                </div>
            </nav>