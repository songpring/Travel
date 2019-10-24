<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 발송</title>
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
							<div class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h2>위트래블</h2>
									<h1>쿠폰 발송</h1>
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
			<h1>쿠폰 발송</h1>
			<form action='<c:url value="/receiveCoupon"/>' method="post" name="fr">
				<div class="form-group2">
					<label for="coupon_type">쿠폰 종류</label> 
					<input type="text" class="form-control2" id="coupon_type" name="coupon_type">
				</div>
				
				<!-- 회원 목록 리스트 넣기!!!!!!! -->	
			 	<!-- 회원 클릭선택 후 추가하면 새로운 리스트에 저장된후 발송을 누르면 각각의 회원의 coupon에 값이 들어가게하기!!!   -->
			 	<!-- 그렇게 만들고 싶어요...ㅜㅜ 히히 -->
			 	<!-- 아 새로운 리스트에 회원을 추가했는데 선택후 하나의 회원만 제거하고 싶을때 버튼 하나 생성하기!! -->
			 	<input type="reset" value="제거" class="btn btn-sm btn-denger">
			 	
				<div class="form-group2">
					<button type="submit" class="btn btn-sm btn-success" id="btnsub" name="btnsub">쿠폰 발송하기</button>
					<!-- <input type="submit" name="btnsub" id="btnsub" value="쿠폰 추가" class="btn btn-primary"> -->
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