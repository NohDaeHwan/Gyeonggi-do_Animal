<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

  <!-- Page Content-->
  <section class="py-5">
    <div class="container">
      <!-- Page Heading/Breadcrumbs-->
      <h1><strong>동물 약국 조회</strong></h1>
      <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/index.jsp">홈</a>
        </li>
        <li class="breadcrumb-item active">동물 약국 조회</li>
      </ol>
      <!-- /.Page Heading -->
    
      <!-- Search Widget -->
      <div class="card mb-4">
        <div class="card-body">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for...">
            <span class="inpug-group-append">
              <button class="btn btn-secondary" type="button">Go!</button>
            </span>
          </div>
        </div>
      </div>
      <!-- /.Search Widget -->
    
      <!-- Page Content -->
      <div class="table-responsive">
        <table class="table" style="text-align: center; border: 1px solid #dddddd">
          <thead>
    	    <tr>
    	      <th style="background-color: #fafafa; text-align: center;">시군명</th>
    	      <th style="background-color: #fafafa; text-align: center;">사업장명</th>
    	      <th style="background-color: #fafafa; text-align: center;">영업상태</th>
    	      <th style="background-color: #fafafa; text-align: center;">우편번호</th>
    	      <th style="background-color: #fafafa; text-align: center;">도로명주소</th>
    	    </tr>
          </thead>
          <tbody>
          <c:forEach var="animal" items="${animals}">
    	    <tr>
              <td>${animal.SIGUN_NM}</td>
              <td>${animal.BIZPLC_NM}</td>
              <td>${animal.BSN_STATE_NM}</td>
              <td>${animal.ROADNM_ZIP_CD}</td>
              <td>${animal.REFINE_ROADNM_ADDR}</td>
            </tr>
	      </c:forEach>
          </tbody>
        </table>
      </div>
      <!-- /.Page Content -->

      <!-- Pagination -->
      <ul class="pagination justify-content-center">
        <li class="page-item">
        <c:choose>
		  <c:when test="${param.page == 0}">
		    <a class="page-link">&laquo;</a>
		  </c:when>
		  <c:otherwise>
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=0">&laquo;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>
    
        <li class="page-item">
        <c:choose>
		  <c:when test="${param.page == 0}">
		    <a class="page-link">&lt;</a>
		  </c:when>
		  <c:otherwise>
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=${param.page - 1}">&lt;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>
	  
	    <c:if test="${param.page >= 4 && param.page < (lastPage - 5)}">
	      <c:forEach var="num" begin="0" end="7">
	        <li class="page-item">
              <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=${param.page + num - 2}">${param.page + num - 1}</a>
	        </li>
	      </c:forEach>
	    </c:if>
	   
	    <c:if test="${param.page >= (lastPage - 5)}">
	      <c:forEach var="num" begin="0" end="7">
	        <li class="page-item">
              <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=${lastPage - (7 - num)}">${lastPage - (6 - num)}</a>
	        </li>
	      </c:forEach>
	    </c:if>
	   
		<c:if test="${param.page <= 3}">
		  <c:forEach var="num" begin="0" end="7">
	        <li class="page-item">
              <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=${num}">${num + 1}</a>
	        </li>
	      </c:forEach>
		</c:if>

        <li class="page-item">
        <c:choose>
		  <c:when test="${param.page == lastPage}">
		    <a class="page-link">&gt;</a>
	      </c:when>
		  <c:otherwise>
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=${param.page + 1}">&gt;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>
	  
	    <li class="page-item">
        <c:choose>
		  <c:when test="${param.page == lastPage}">
		    <a class="page-link">&raquo;</a>
	      </c:when>
		  <c:otherwise>
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalpharmacy&page=${lastPage}">&raquo;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>  	  
	  </ul>
	  <!-- /.Pagination -->
    </div>
  </section>
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
