<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

  <!-- Container -->
  <div class="container">

    <!-- Page Heading -->
    <h1 class="mt-4 mb-3"><strong style="color: #343a40;">회원가입</strong></h1>

    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="<%=request.getContextPath()%>/index.jsp">홈</a>
      </li>
      <li class="breadcrumb-item active">회원가입</li>
    </ol>
    <!-- /.Page Heading -->

    <!-- Page Content -->
    <div class="row">
      <div class="col-lg-8 mb-4">
        <form action="<%=request.getContextPath()%>/user?cmd=join" method="post" onsubmit="return valid()">
          <div class="control-group form-group">
            <div class="controls">
              <label for="id"><strong style="color: #343a40;">아이디 :</strong> </label>
              <button type="button" class="btn btn-info btn-sm" onclick="usernameCheck();">중복검사</button>
              <input type="text" class="form-control" id="id" name="id" required >
              <p class="help-block"></p>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label for="password"><strong style="color: #343a40;">비밀번호 :</strong></label>
              <input type="password" class="form-control" id="password" name="password" required>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label for="email"><strong style="color: #343a40;">이메일 :</strong></label>
              <input type="email" class="form-control" id="email" name="email" required>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label for="address"><strong style="color: #343a40;">주소 :</strong></label>
              <button type="button" class="btn btn-info btn-sm" onclick="goPopup();">주소검색</button>
              <input type="text" class="form-control" id="roadAddress" name="roadAddress" required readonly><br>
              <input type="text" class="form-control" id="jibunAddress" name="jibunAddress" required readonly>
            </div>
          </div>
          <button type="submit" class="btn btn-primary">회원가입</button>
        </form>
      </div>
    </div>
    <!-- /.Page Content -->

  </div>
  <!-- /.Container -->
  
  <script>
  	var isChecking = false;
  	
  	function valid() {
  		if (isChecking == false) {
  			alert('아이디 중복체크를 해주세요');
  		}
  		return isChecking;
  	}
  	
  	function usernameCheck() {
  		var username = $("#id").val();
  		if (username == '') {
  			alert("아이디를 입력해주세요~!!");
  			return;
  		}
  		
  		$.ajax({
  			type: "POST",
  			url: "/animal/user?cmd=usernameCheck",
  			data: username,
  			contentType: "text/plain; charset=utf-8",
  			dataType: "text"
  		}).done(function(data) {
  			if (data === 'ok') {
  				isChecking = false;
  				alert('이미 사용중인 아이디입니다.');
  			} else {
  				isChecking = true;
  				alert('해당 아이디를 사용할 수 있습니다.');
  				$("#id").attr("readonly", true);
  			}
  		});
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

<%@ include file="../layout/footer.jsp" %>