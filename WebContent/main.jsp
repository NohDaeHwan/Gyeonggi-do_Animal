<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

  <!-- Header Section -->
  <header>
    <div class="carousel slide" id="carouselExampleIndicators" data-ride="carousel">
      <ol class="carousel-indicators">
        <li class="active" data-target="#carouselExampleIndicators" data-slide-to="0"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <!-- Slide One - Set the background image for this slide in the line below -->
        <div class="carousel-item active">
          <img class="d-block w-100" src="img/MainImage1.jpg" alt="..." />
          <div class="carousel-caption d-none d-md-block">
            <h3> Animal Hospital </h3>
            <p> 동물 병원 조회 </p>
          </div>
        </div>
        <!-- Slide Two - Set the background image for this slide in the line below -->
        <div class="carousel-item">
          <img class="d-block w-100" src="img/MainImage2.jpg" alt="..." />
          <div class="carousel-caption d-none d-md-block">
            <h3> Animal Drugstore </h3>
            <p> 동물 약국 조회 </p>
          </div>
        </div>
        <!-- Slide Three - Set the background image for this slide in the line below -->
        <div class="carousel-item">
          <img class="d-block w-100" src="img/MainImage3.jpg" alt="..." />
          <div class="carousel-caption d-none d-md-block">
            <h3> Abandoned Animal Protection Center </h3>
            <p> 유기 동물 보호 시설 조회 </p>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </header>
  <!-- /.Header Section -->

  <!-- Page Content-->
  <section class="py-5 bg-light">
    <div class="container">
      <h1 class="mb-4"> 경기도 시설 알아보기 </h1>
      <!-- Marketing Icons Section-->
      <div class="row">
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="card h-100">
            <h4 class="card-header">동물 병원 조회</h4>
            <div class="card-body">
            <c:choose>
          	  <c:when test="${hosptls != null}">
          		<c:forEach var="hosptl" items="${hosptls}">
          		  <p class="card-text">${hosptl.REFINE_ROADNM_ADDR}<br></p>
          		</c:forEach>
          		<a href="<%=request.getContextPath()%>/user?cmd=animalhosptl" style="text-decoration: none;">내 주변 동물병원 보러가기 --></a>
          	  </c:when>
          	  <c:otherwise><p class="card-text">근처 어디에 무슨 병원이 있었더라? <br> '동물 병원 조회'로 쉽고 상세하게 <br> 내 주변 동물 병원을 찾아보세요!</p></c:otherwise>
            </c:choose>
            </div>
            <div class="card-footer">
              <a class="btn btn-dark" href="<%=request.getContextPath()%>/search?cmd=animalhosptl&page=1&data=0"> 검색 </a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="card h-100">
            <h4 class="card-header">동물 약국 조회</h4>
            <div class="card-body">
            <c:choose>
          	  <c:when test="${pharmacys != null}">
          	    <c:forEach var="pharmacy" items="${pharmacys}">
          	      <p class="card-text">${pharmacy.REFINE_ROADNM_ADDR}</p>
          	    </c:forEach>
          	    <a href="<%=request.getContextPath()%>/user?cmd=animalpharmacy" style="text-decoration: none;">내 주변 동물약국 보러가기 --></a>
          	  </c:when>
          	  <c:otherwise><p class="card-text">다양하고 정확한 동물 약국 정보를 찾아보세요!</p></c:otherwise>
            </c:choose>
            </div>
            <div class="card-footer">
              <a class="btn btn-dark" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=1&data=0"> 검색 </a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="card h-100">
            <h4 class="card-header">유기 동물 보호 시설 조회</h4>
            <div class="card-body"><p class="card-text">유기 동물들을 보호하고 있는 시설들을 찾아보세요!</p></div>
            <div class="card-footer">
              <a class="btn btn-dark" href="<%=request.getContextPath()%>/search?cmd=animalfacilit&page=1&data=0"> 검색 </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <hr class="my-0" />

  <!-- Animal Section -->
  <section class="py-5 bg-light">
    <div class="container">
      <h2 class="mb-4"> 쉽게 알려주는 반려 동물 질병 사전 </h2>
      <div class="row">
        <div class="col-lg-4 col-sm-6 mb-4">
          <div class="card h-100">
            <a href="knowledge/knowledge1.jsp"><img class="card-img-top" src="img/bone.png" alt="" /></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="knowledge/knowledge1.jsp"> 강아지 슬개골 탈구 증상과 치료, 관리법은? </a>
              </h4>
              <p class="card-text"> What are the symptoms, treatment, and management of dislocation of the dog's skull? </p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-sm-6 mb-4">
          <div class="card h-100">
            <a href="knowledge/knowledge2.jsp"><img class="card-img-top" src="img/antibacterial.png" alt="" /></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="knowledge/knowledge2.jsp"> 강아지 알레르기 증상과 원인에 따른 관리법은? </a>
              </h4>
              <p class="card-text"> How do you manage your dog allergy symptoms and causes? </p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-sm-6 mb-4">
          <div class="card h-100">
            <a href="knowledge/knowledge3.jsp"><img class="card-img-top" src="img/heart.png" alt="" /></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="knowledge/knowledge3.jsp"> 강아지 심장 질환, 기침한다면 의심해야 해요! </a>
              </h4>
              <p class="card-text"> Dog heart disease, if you cough, you should doubt it! </p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-sm-6 mb-4">
          <div class="card h-100">
            <a href="knowledge/knowledge4.jsp"><img class="card-img-top" src="img/leech.png" alt="" /></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="knowledge/knowledge4.jsp"> 고양이 방광염, 관리가 가장 중요해요! </a>
              </h4>
              <p class="card-text"> Cat bladder inflammation, care is the most important thing! </p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-sm-6 mb-4">
          <div class="card h-100">
            <a href="knowledge/knowledge5.jsp"><img class="card-img-top" src="img/dentist.png" alt="" /></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="knowledge/knowledge5.jsp"> 고양이 구내염? 대표적인 치과 질병 알아보기 </a>
              </h4>
              <p class="card-text"> Cat's canker sore? Discover Typical Dental Diseases </p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-sm-6 mb-4">
          <div class="card h-100">
            <a href="knowledge/knowledge6.jsp"><img class="card-img-top" src="img/pills.png" alt="" /></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="knowledge/knowledge6.jsp"> 고양이 허피스, 그냥 감기라고 생각하면 안 돼요! </a>
              </h4>
              <p class="card-text"> Herpes, you can't just think of it as a cold! </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <hr class="my-0" />
  <!-- /.Animal Section -->
   
  <!-- /.Container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Animal Protector of Gyeonggi Provience 2021 </p>
    </div>
  </footer>
  <!-- /.Footer -->

  <!-- Bootstrap core JS-->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <script src="js/scripts.js"></script>

</body>

</html>