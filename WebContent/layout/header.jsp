<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>

  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>경기 동물 지킴이</title>

  <!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Font Awesome icons (free version)-->
  <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="css/styles.css" rel="stylesheet" />
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet" />

</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">경기 동물 지킴이</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/about.jsp">소개</a></li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              메뉴
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
              <a class="dropdown-item" href="<%=request.getContextPath()%>/search/animalHosptl.jsp">동물 병원</a>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/search/animalPharmacy.jsp">동물 약국</a>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/search/animalFacilit.jsp">유기 동물 보호 시설</a>
            </div>
          </li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/user/loginForm.jsp">로그인</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/user/joinForm.jsp">회원가입</a></li>          
        </ul>
      </div>
    </div>
  </nav>
  <!-- /.Navigation -->