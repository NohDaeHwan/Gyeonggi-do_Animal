<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

  <!-- Container -->
  <div class="container">

    <!-- Page Heading -->
    <h1 class="mt-4 mb-3"><strong style="color: #343a40;">로그인</strong></h1>

    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="<%=request.getContextPath()%>/index.jsp">홈</a>
      </li>
      <li class="breadcrumb-item active">로그인</li>
    </ol>
    <!-- /.Page Heading -->

    <!-- Page Content -->
    <div class="row">
      <div class="col-lg-8 mb-4">
        <form action="<%=request.getContextPath()%>/user?cmd=login" method="post">
          <div class="control-group form-group">
            <div class="controls">
              <label for="id"><strong style="color: #343a40;">아이디 : </strong></label>
              <input type="text" class="form-control" id="id" name="id" required>
              <p class="help-block"></p>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label for="password"><strong style="color: #343a40;">비밀번호 : </strong></label>
              <input type="password" class="form-control" id="password" name="password" required>
            </div>
          </div>
          <div id="success"></div>
          <!-- For success/fail messages -->
          <button type="submit" class="btn btn-primary">로그인</button>
          <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/user?cmd=joinForm'">회원가입</button>
        </form>
      </div>
    </div>
    <!-- /.Page Content -->

  </div>
  <!-- /.Container -->

<%@ include file="../layout/footer.jsp" %>