<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

  <!-- Page Content-->
  <section class="py-5">
    <div class="container">
      <!-- Page Heading/Breadcrumbs-->
      <h1><strong style="color: #343a40;">유기 동물 보호 시설 조회</strong></h1>
      <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/index.jsp">홈</a>
        </li>
        <li class="breadcrumb-item active">유기 동물 보호 시설 조회</li>
      </ol>
      <!-- /.Page Heading -->
    
      <!-- Page Content -->
      <div class="table-responsive">
        <table class="table" style="text-align: center; border: 1px solid #dddddd">
          <thead>
    	    <tr>
              <th style="background-color: #fafafa; text-align: center;">시군명</th>
    	      <th style="background-color: #fafafa; text-align: center;">사업장명</th>
    	      <th style="background-color: #fafafa; text-align: center;">전화번호</th>
    	      <th style="background-color: #fafafa; text-align: center;">우편번호</th>
    	      <th style="background-color: #fafafa; text-align: center;">도로명주소</th>
    	    </tr>
          </thead>
          <tbody>
          <c:forEach var="animal" items="${animals}">
    	    <tr>
              <td>${animal.SIGUN_NM}</td>
              <td>${animal.ENTRPS_NM}</td>
          	  <td>${animal.ENTRPS_TELNO}</td>
          	  <td>${animal.REFINE_ZIP_CD}</td>
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
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalfacilit&page=0">&laquo;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>
    
        <li class="page-item">
        <c:choose>
		  <c:when test="${param.page == 0}">
		    <a class="page-link">&lt;</a>
		  </c:when>
		  <c:otherwise>
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalfacilit&page=${param.page - 1}">&lt;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>
	  
	    <c:forEach var="num" begin="0" end="${lastPage}">
	      <c:choose>
		  	<c:when test="${param.page == num}">
		  	  <li class="page-item">
            	<a class="page-link"><strong>${num + 1}</strong></a>
	      	  </li>
		  	</c:when>
		  	<c:otherwise>
		      <li class="page-item">
            	<a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalfacilit&page=${num}">${num + 1}</a>
	      	  </li>
		  	</c:otherwise>
	      </c:choose>   
	    </c:forEach>

        <li class="page-item">
        <c:choose>
		  <c:when test="${param.page == lastPage}">
		    <a class="page-link">&gt;</a>
	      </c:when>
		  <c:otherwise>
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalfacilit&page=${param.page + 1}">&gt;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>
	  
	    <li class="page-item">
        <c:choose>
		  <c:when test="${param.page == lastPage}">
		    <a class="page-link">&raquo;</a>
	      </c:when>
		  <c:otherwise>
		    <a class="page-link" href="<%=request.getContextPath()%>/search?cmd=animalfacilit&page=${lastPage}">&raquo;</a>
		  </c:otherwise>
	    </c:choose>
	    </li>     	  
	  </ul>
	  <!-- /.Pagination -->
    </div>
  </section>
  <!-- /.Container -->

<%@ include file="../layout/footer.jsp" %>