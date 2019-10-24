<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 정보</title>
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
									<h1>쿠폰 정보</h1>
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
			<h1>쿠폰 정보</h1>
			<form action='<c:url value="/couponInformation"/>' method="post" name="fr">
				<h6>-------------------쿠폰 상세정보----------------</h6>
				<div class="form-group2">
					<label for="coupon_type">쿠폰 종류</label> 
					<input type="text" class="form-control2" id="coupon_type" name="coupon_type">
				</div>
				<div class="form-group2">
					<label for="coupon_name">쿠폰 이름</label> 
					<input type="text" class="form-control2" id="coupon_name" name="coupon_name">
				</div>
				<div class="form-group2">
					<label for="coupon_content">쿠폰 내용</label> 
					<input type="text" class="form-control2" id="coupon_content" name="coupon_content" style="height: 5em;">
				</div>
		
				<div class="col-md-2">
					<div class="form-group">
						<label for="conpon_start_day">쿠폰 시작일:</label>
						<div class="form-field">
							<i class="icon icon-calendar2"></i> 
							<input type="text" id="conpon_start_day" name="conpon_start_day" class="form-control date">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="conpon_end_day">쿠폰 만료일:</label>
						<div class="form-field">
							<i class="icon icon-calendar2"></i> 
							<input type="text" id="conpon_end_day" name="conpon_end_day" class="form-control date" >
						</div>
					</div>
				</div>
			
				<div class="form-group2">
					<button type="submit" class="btn btn-sm btn-success" id="btnsub" name="btnsub">쿠폰 추가</button>
					<!-- <input type="submit" name="btnsub" id="btnsub" value="쿠폰 추가" class="btn btn-primary"> -->
					<button type="reset" class="btn btn-sm btn-danger" id="btnCancel">취소</button>
					<!-- <input type="reset" name="btnsub" id="btnsub" value="취소" class="btn btn-delete"> -->
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