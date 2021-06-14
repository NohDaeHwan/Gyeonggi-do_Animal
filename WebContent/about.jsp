<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

  <!-- Page Content-->
  <section class="py-5">
    <div class="container">
      <!-- Page Heading/Breadcrumbs-->
      <h1><strong style="color: #343a40;">소개</strong></h1>
      <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/index.jsp">홈</a>
        </li>
        <li class="breadcrumb-item active">소개</li>
      </ol>
      <!-- /.Page Heading -->

      <!-- Intro Content -->
      <div class="row">
        <div class="col-lg-6">
          <img class="img-fluid rounded mb-4" src="img/logo.jpg" style="height:400px; width:500px;" alt="..." />
        </div>
        <div class="col-lg-6">
          <br /><br /><br /><h2><strong style="color: #343a40;"> 경기도 동물 지킴이 </strong></h2><br />
          <p>경기데이터드림에서 제공하는 공공 데이터 포털 API를 통해서 필요한 정보만 가져와 사용자들에게 편리한 UI와 정보를 제공해줍니다.<p>
          <p>또한 회원으로 가입 할 경우 회원의 주소 주변 동물병원 및 동물약국도 확인 할 수 있습니다.</p>
          <p>만약 동물병원에 방문했으면 마이페이지에서 동물일지를 작성하여 기록을 남길 수 있고, 방문한 병원에 대해 평점도 매길 수 있습니다.</p>
        </div>
      </div>
    </div>
  </section>
  <hr class="my-0" />
  
  <!-- Team Members -->
  <section class="py-5 bg-light">
    <div class="container">
      <h2 class="mb-4"><strong style="color: #343a40;">제작자</strong></h2>
      <div class="row">
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="card h-100 text-center">
            <img class="card-img-top" src="img/노대환.PNG" alt="..." />
            <div class="card-body">
              <h4 class="card-title">노대환</h4>
              <h6 class="card-subtitle mb-2 text-muted">프로그래밍, 웹 디자인, 데이터베이스 설계</h6>
            </div>
            <div class="card-footer">
              <a href="http://www.kyungmin.ac.kr/site/kmc/main.do">ndh5953@kyungmin.ac.kr</a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="card h-100 text-center">
            <img class="card-img-top" src="img/김수민.PNG" alt="">
            <div class="card-body">
              <h4 class="card-title">김수민</h4>
              <h6 class="card-subtitle mb-2 text-muted">프로그래밍, 웹 디자인, 데이터베이스 설계</h6>
            </div>
            <div class="card-footer">
              <a href="http://www.kyungmin.ac.kr/site/kmc/main.do">resia@kyungmin.ac.kr</a>
            </div>
          </div>
        </div>
      </div>   
    </div>
  </section>
  <hr class="my-0" />
  <!-- /.Team Members -->

  <!-- /.Container -->

<%@ include file="layout/footer.jsp" %>