<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

  <!-- Page Content-->
  <section class="py-5">
    <div class="container">
      <!-- Page Heading/Breadcrumbs-->
      <h1><strong>마이페이지</strong></h1>
      <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/index.jsp">홈</a>
      	</li>
      	<li class="breadcrumb-item active">마이페이지</li>
      </ol>
      <!-- /.Page Heading -->

      <!-- Page Content -->
      <div class="row">
        <!-- Sidebar Column --> <%-- 사이드바 --%>
      	<div class="col-lg-3 mb-4">
          <div class="list-group">
            <a href="#" class="list-group-item" style="color: black;">메인</a>
          	<a href="#" class="list-group-item" style="color: black;">진료 기록하기</a>
          	<a href="#" class="list-group-item" style="color: black;">내 주변 동물병원</a>
          	<a href="#" class="list-group-item" style="color: black;">내 주변 동물약국</a>
          	<a href="#" class="list-group-item" style="color: black;">회원정보수정</a>
          	<a href="#" class="list-group-item" style="color: black;">비밀번호변경</a>
          	<a href="#" class="list-group-item" style="color: black;">회원탈퇴</a>
          </div>
      	</div>
      	<!-- /.Sidebar Column -->
      
      	<!-- Content Column -->
      	
      	
      	<!-- /.Content Column -->
  
	  </div>
      <!-- /.Page Content -->
    </div>
  </section>
  <!-- /.Container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
  </footer>
  <!-- /.Footer -->

  <!-- Bootstrap core JS-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <script src="js/scripts.js"></script>

</body>

</html>
