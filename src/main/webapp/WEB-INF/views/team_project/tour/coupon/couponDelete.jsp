<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 삭제</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->
<script src="./resources/assets/tour/js/common.js"></script>
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
				<li style="background-image: url(images/cover-img-2.jpg);">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h2>위트래블</h2>
									<h1>쿠폰 삭제</h1>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</aside>


	<div id="colorlib-blog">
		<div class="container">
			<%
					//세션값 가져와서 변수에 저장
					String id = (String) session.getAttribute("id");
				%>
			<h1>쿠폰 삭제</h1>
			<form action='<c:url value="/couponDelete"/>' method="post" name="fr">
				<div class="form-group2">
					<label for="coupon_type">아이디</label> 
					<input type="text" class="form-control2" id="id" name="id" value="<%=id%>" readonly>
				</div>
				<div class="form-group2">
					<label for="coupon_type">비밀번호</label> 
					<input type="text" class="form-control2" id="password" name="password">
				</div>
				<div class="form-group2">
					<button type="submit" class="btn btn-sm btn-success" id="btnsub" name="btnsub">쿠폰 삭제</button>
				</div>
			</form>
		</div>
	</div>
	
	
	<!-- 맨위로 가게하는 버튼 -->
	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp" />
</body>
</html>