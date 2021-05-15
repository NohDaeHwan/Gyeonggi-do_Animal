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
            <a href="<%=request.getContextPath()%>/journal?cmd=list&page=0&userId=${sessionScope.User.id}" class="list-group-item" style="color: black;">메인</a>
          	<a href="<%=request.getContextPath()%>/journal?cmd=journalRecordForm" class="list-group-item" style="color: black;">진료 기록하기</a>
          	<a href="<%=request.getContextPath()%>/user?cmd=animalhosptl" class="list-group-item" style="color: black;">내 주변 동물병원</a>
          	<a href="<%=request.getContextPath()%>/user?cmd=animalpharmacy" class="list-group-item" style="color: black;">내 주변 동물약국</a>
          	<a href="<%=request.getContextPath()%>/user?cmd=PasswordCheckForm" class="list-group-item" style="color: black;">회원정보수정</a>
          	<a href="<%=request.getContextPath()%>/user?cmd=PasswordUpdateForm" class="list-group-item" style="color: black;">비밀번호변경</a>
          	<a href="<%=request.getContextPath()%>/user?cmd=UserWithdrawal" class="list-group-item" style="color: black;">회원탈퇴</a>
          </div>
      	</div>
      	<!-- /.Sidebar Column -->
      
      	<!-- Content Column -->
      	<c:if test="${animals != null}"> <%-- 내 주변 동물병원 || 내 주변 동물약국 보여주기 --%>
      	<div class="col-lg-9 mb-4">
            <table class="table table-bordered table-sm">
      	      	<thead>
    		    	<tr>
    	  		  		<th style="background-color: #fafafa; text-align: center;">사업장명</th>
    	  		  		<th style="background-color: #fafafa; text-align: center;">상태</th>
    	  		  		<th style="background-color: #fafafa; text-align: center;">전화번호</th>
    	  	 	  		<th style="background-color: #fafafa; text-align: center;">도로명주소</th>
    	  		  		<th style="background-color: #fafafa; text-align: center;">평점</th>
    				</tr>
      	      	</thead>
      	      	<tbody>
      		   		<c:forEach var="animal" items="${animals}">
    				<tr>
         		  		<td style="text-align: center; font-size: small;">${animal.BIZPLC_NM}</td>
          		  		<td style="text-align: center; font-size: small;">${animal.BSN_STATE_NM}</td>
          		  		<td style="text-align: center; font-size: small;">${animal.LOCPLC_FACLT_TELNO}</td>
          		  		<td style="text-align: center; font-size: small;">${animal.REFINE_ROADNM_ADDR}</td>
          		  		<c:if test="${animal.TOTAL_RANK == ''}"><td style="text-align: center; font-size: small;"><strong> X </strong></td></c:if>
          		  		<c:if test="${animal.TOTAL_RANK != ''}"><td style="text-align: center; font-size: small;"><strong>5 / ${animal.TOTAL_RANK}</strong></td></c:if>       		  		
        			</tr>
	  				</c:forEach>
      		  	</tbody>
    		</table>
      	</div>
      	</c:if>
      	
      	<c:if test="${journalRecordForm == true}"> <%-- 동물 진료 일지 등록하기 --%>
      		<div class="col-lg-9 mb-4">
      			<form action="<%=request.getContextPath()%>/journal?cmd=journalRecord&page=0" method="post">
      				<input type="hidden" name="userId" value="${sessionScope.User.id}" />
      				<div class="form-group">
      					<label for="title"><strong>제목:</strong></label>
      					<input type="text" class="form-control" placeholder="제목을 입력해주세요~!!" id="title" name="title" />
      				</div>
      				<div class="form-group">
      					<label for="treatDate"><strong>진료일자:</strong></label>
      					<input type="date" class="form-control" id="treatDate" name="treatDate" />
      				</div>
      				<div class="form-group">
      					<label for="visit"><strong>방문한병원:</strong></label>
      					<select class="form-control" name="visit" id="visit">
      						<c:forEach var="animal" items="${animalDto}">
      						<option value="${animal.BIZPLC_NM}">${animal.BIZPLC_NM}</option>
      						</c:forEach>
      					</select>
      					<input type="hidden" name="address" value="${address}">
      				</div>
      				<div class="form-group">
      					<label for="rank"><strong>평점:</strong></label>
      					<select class="form-control" name="rank" id="rank">
      						<option value="1">1점</option>
      						<option value="2">2점</option>
      						<option value="3">3점</option>
      						<option value="4">4점</option>
      						<option value="5">5점</option>
      					</select>
      				</div>
      				<div class="form-group">
      					<label for="content"><strong>내용:</strong></label>
      					<textarea id="summernote" class="form-control" rows="5" id="content" name="content"></textarea>
      				</div>
      				<button type="submit" class="btn btn-dark">진료일지 등록</button>
      			</form>
      		</div>
      	</c:if>  
      	
      	<c:if test="${journals != null}"> <%-- 동물 진료 일지 보여주기 --%>
      	<div class="col-lg-9 mb-4">  
      		<div class="progress m-1">		
      		<div class="progress-bar bg-dark" style="width:${currentPercent}%"></div>
      		</div>
      		<c:forEach var="journal" items="${journals}">
      		<div class="card m-1">
      			<div class="card-body">
      				<h4 class="card-title"><a href="/animal/journal?cmd=detail&id=${journal.id}" style="color: black;">제목 : ${journal.title}</a></h4>
      				<h5 class="card-title">진료일자 : ${journal.treatDate}</h5>
      				<h6 class="card-title">생성일자 : ${journal.createDate}</h6>
      			</div>
      		</div>
      		</c:forEach>
      		<ul class="pagination justify-content-center">
      			<c:choose>
      			<c:when test="${param.page == 0}">
      				<li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
      			</c:when>
      			<c:otherwise>
      				<li class="page-item"><a class="page-link" href="/animal/journal?cmd=list&page=${param.page - 1}&userId=${sessionScope.User.id}">이전</a></li>
      			</c:otherwise>
      			</c:choose>
      			
      			<c:choose>
      			<c:when test="${param.page == lastPage}">
      				<li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
      			</c:when>
      			<c:otherwise>
      				<li class="page-item"><a class="page-link" href="/animal/journal?cmd=list&page=${param.page + 1}&userId=${sessionScope.User.id}">다음</a></li>
      			</c:otherwise>
      			</c:choose>
      		</ul>
      	</div>
      	</c:if>
      	
      	<c:if test="${journal != null}"> <%-- 동물 진료 일지 상세보기 --%>
      		<div class="col-lg-9 mb-4">
      			<h3 class="m-2">제목 : ${journal.title}</h3>
      			<h3 class="m-2">진료일자 : ${journal.treatDate}</h3>
      			<h6 class="m-2">작성일자 : ${journal.createDate}</h6>
      			<hr />
      			<div class="form-group">
      				<div class="m-2">${journal.content}</div>
      			</div>
      		</div>
      	</c:if>
      	
      	<c:if test="${passwordCheck == true}"> <%-- 회원정보 수정하기 전 비밀번호 확인하기 --%>
      	<div class="col-lg-8 mb-4">
        	<form action="<%=request.getContextPath()%>/user?cmd=userUpdateForm" method="post" onsubmit="return valid()">
          		<div class="control-group form-group">
            		<div class="controls">
              			<label for="id"><strong>아이디 :</strong> </label>
              			<input type="text" class="form-control" id="id" name="id" value="${sessionScope.User.username}" required readonly>
              			<p class="help-block"></p>
            		</div>
          		</div>
          		<div class="control-group form-group">
            		<div class="controls">
              			<label for="password"><strong>비밀번호 :</strong></label>
              			<input type="password" class="form-control" id="password" name="password" required>
            		</div>
          		</div>
          		<button type="submit" class="btn btn-dark">확인</button>
        	</form>
      	</div>
      	</c:if>
      	
      	<c:if test="${update == true}"> <%-- 비밀번호 체크가 완료되면 회원정보수정 페이지 보여주기 --%>
      	<div class="col-lg-8 mb-4">
        	<form action="#" method="post">
          		<div class="control-group form-group">
            		<div class="controls">
              			<label for="userid"><strong>아이디 :</strong> </label>
              			<input type="text" class="form-control" id="userid" name="userid" value="${sessionScope.User.username}" required readonly>
              			<p class="help-block"></p>
            		</div>
          		</div>
          		<div class="control-group form-group">
            		<div class="controls">
              			<label for="email"><strong>이메일 :</strong></label>
              			<input type="email" class="form-control" id="email" name="email" value="${sessionScope.User.email}" required>
            		</div>
          		</div>
          		<div class="control-group form-group">
            		<div class="controls">
              			<label for="address"><strong>주소 :</strong></label>
              			<button type="button" class="btn btn-dark btn-sm" onclick="goPopup();">주소검색</button>
              			<input type="text" class="form-control" id="roadAddress" name="roadAddress" value="${sessionScope.User.roadAddress}" required readonly><br>
              			<input type="text" class="form-control" id="jibunAddress" name="jibunAddress" value="${sessionScope.User.jibunAddress}" required readonly>
            		</div>
          		</div>
          		<button type="button" class="btn btn-dark" onclick="userUpdate();">회원정보수정</button>
        	</form>
      	</div>
      	</c:if>	  
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
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

  <script>	
	var isChecking = false;
	
	function valid() {
		var username = $("#id").val();
		var password = $("#password").val();
		console.log(username, password);
		
		$.ajax({
			type: "POST",
			url: "/animal/user?cmd=passwordCheck",
			data: {username : username, password : password},
			dataType: "text"
		}).done(function(data) {
			if (data === 'ok') {
				isChecking = true;
			} else {
				alert('비밀번호가 일치하지 않습니다');
				isChecking = false;
			}
		});
		
		return isChecking;
	}
	
	function userUpdate() {
		var result = confirm('회원정보를 수정하시겠습니까?');

		if (result == true) {
			var username = $("#userid").val();
			var email = $("#email").val();
			var roadAddress = $("#roadAddress").val();
			var jibunAddress = $("#jibunAddress").val();
			
			console.log(username, email, roadAddress, jibunAddress);
			$.ajax({
				type: "POST",
				url: "/animal/user?cmd=userUpdate",
			    data: {username: username, email: email, roadAddress: roadAddress, jibunAddress: jibunAddress},
			    dataType: "text"
			}).done(function(data) {
				if (data === 'ok') {
					alert('회원정보를 수정했습니다');
					window.location.reload();
				} else {
					alert('회원정보 수정을 실패했습니다');
				}
			});
		} 
		
	}
	
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/animal/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
  	
  	function jusoCallBack(roadFullAddr,jibunAddr) {
  		var roadAddress = document.querySelector("#roadAddress");
  		var jibunAddress = document.querySelector("#jibunAddress");
  		roadAddress.value = roadFullAddr;
  		jibunAddress.value = jibunAddr;
	}
  </script>

  <script>
	$('#summernote').summernote({
		placeholder: '글을 쓰세요.',
		tabsize: 2,
		height: 400
	});
  </script>

</body>

</html>
