<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

  <!-- Page Content-->
  <section class="py-5">
    <div class="container">
      <!-- Page Heading/Breadcrumbs-->
      <h1><strong>소개</strong></h1>
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
          <img class="img-fluid rounded mb-4" src="img/animal-rights.png" alt="..." />
        </div>
        <div class="col-lg-6">
          <h2> Animal Protector of Gyeonggi Provience </h2>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sed voluptate nihil eum consectetur similique? Consectetur, quod, incidunt, harum nisi dolores delectus reprehenderit voluptatem perferendis dicta dolorem non blanditiis ex fugiat.</p>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe, magni, aperiam vitae illum voluptatum aut sequi impedit non velit ab ea pariatur sint quidem corporis eveniet. Odit, temporibus reprehenderit dolorum!</p>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Et, consequuntur, modi mollitia corporis ipsa voluptate corrupti eum ratione ex ea praesentium quibusdam? Aut, in eum facere corrupti necessitatibus perspiciatis quis?</p>
        </div>
      </div>
    </div>
  </section>
  <hr class="my-0" />
  
  <!-- Team Members -->
  <section class="py-5 bg-light">
    <div class="container">
      <h2 class="mb-4">Our Team</h2>
      <div class="row">
        <div class="col-lg-4 mb-4 mb-lg-0">
          <div class="card h-100 text-center">
            <img class="card-img-top" src="img/노대환.PNG" alt="..." />
            <div class="card-body">
              <h4 class="card-title">노대환</h4>
              <h6 class="card-subtitle mb-2 text-muted">프로그래밍, 웹 디자인, 데이터베이스 설계</h6>
              <p class="card-text">개인 설명</p>
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
              <p class="card-text">개인 설명</p>
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
