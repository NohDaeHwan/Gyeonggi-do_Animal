<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

  <!-- Container -->
  <div class="container">

    <!-- Page Heading -->
    <h1 class="mt-4 mb-3"><strong>회원가입</strong></h1>

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
              <label for="id"><strong>아이디 :</strong> </label>
              <button type="button" class="btn btn-info btn-sm" onclick="usernameCheck();">중복검사</button>
              <input type="text" class="form-control" id="id" name="id" required >
              <p class="help-block"></p>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label for="password"><strong>비밀번호 :</strong></label>
              <input type="password" class="form-control" id="password" name="password" required>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label for="email"><strong>이메일 :</strong></label>
              <input type="email" class="form-control" id="email" name="email" required>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label for="address"><strong>주소 :</strong></label>
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
  
  <script>
  	var isChecking = false;
  	
  	function valid() {
  		if (isChecking == false) {
  			alert('아이디 중복체크를 해주세요');
  		}
  		return isChecking;
  	}
  </script>

</body>

</html>
