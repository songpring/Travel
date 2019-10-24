<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>로그인 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->
<script src="./resources/assets/tour/js/common.js"></script>
<script type="text/javascript">
		// 로그인 버튼
// 	$(document).ready(function() {
// 		$("#login-submit").click(function () { login();  });
// 		var login = function() {
// 			if ($('#id').val() ==""){
// 				Toastr("warning","아이디를 입력해주세요");
// 				$("#id").focus();
// 				return false;
// 			}
// 			else if ($('#password').val() ==""){
// 				//alert("아이디를 입력해주세요");
// 				Toastr("warning","비밀번호를 입력해주세요");
// 				$("#password").focus();
// 				return false;
// 			}
// 		};
// 	});
</script>
</head>
<body>
	<div class="colorlib-loader"></div>
	<div id="page"></div>
	<nav class="colorlib-nav" role="navigation">
		<!-- 탑메뉴 들어가는 곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 탑메뉴 들어가는 곳 -->
	</nav>
	<aside id="colorlib-hero">
		<div class="flexslider">
			<ul class="slides">
				<li style="background-image: url(https://images.pexels.com/photos/346885/pexels-photo-346885.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h2>위트래블</h2>
									<h1>로그인</h1>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</aside>
	<article>
		<div id="colorlib-blog">
			<div class="container">
				<form action='<c:url value="/tourlogin"/>' method="post" name="fr" >
					<input type="hidden" name="redirectUrl">
					<fieldset>
						<legend class="screen_out">로그인</legend>
						<div class="box_login">
							<div class="form-group2">
								<label for="id">아이디</label> 
								<input type="text" class="form-control2" id="id" name="id" placeholder="아이디를 입력해주세요" style="ime-mode:inactive;" autocomplete="off">
							</div>
							<div class="form-group2">
								<label for="password">비밀번호</label> 
								<input type="password" class="form-control2" id="password" name="password" placeholder="비밀번호를 입력해주세요">
							</div>
						</div>
				
						<button type="submit" name="login-submit" id="login-submit" class="btn btn-info" onsubmit="onsubmit">로그인</button>
						<button type="button" id="btn1" onclick="location.href='join'" class="btn btn-info">회원가입</button>
					</fieldset>
				</form>
			</div>
		</div>
	</article>

	<!-- 맨위로 가게하는 버튼 -->
	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp" />
</body>
</html>


