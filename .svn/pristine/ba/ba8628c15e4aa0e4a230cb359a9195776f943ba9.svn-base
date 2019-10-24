<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 관리</title>
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
									<h1>쿠폰 관리</h1>
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
			<h1>쿠폰 관리</h1>
			<form action='<c:url value="/couponManagement"/>' method="post" name="fr">
					<div style="float: left;">
						<button type="button" onclick="location.href='receiveCoupon'" class="btn btn-success">쿠폰 발송</button>
					</div>
					<div style="float: left;">
						<button type="button" onclick="location.href='couponissueHistory'" class="btn btn-success">쿠폰 발급내역</button>
					</div>
					<div style="float: right;">
						<button type="button" onclick="location.href='couponInformation'" class="btn btn-sm btn-primary">쿠폰추가</button>
					</div>
				</form>
				<table class="table">
					<thead>
						<tr>
						<th class="header" width="30"><input type="checkbox" id="checkall" /></th>
							<th class="header" width="100">쿠폰 이름</th>
							<th class="header" width="600">쿠폰 내용</th>
							<th class="header" width="150">만료일</th>
							<th class="header" width="300">관리 기능</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox" value="1"> </td>
							<td>?</td>
							<td>?</td>
							<td>?</td>
							<td><button type="button" class="btn btn-success" onclick="location.href='couponUpdate'">수정</button>
								<button type="button" class="btn btn-danger" onclick="location.href='couponDelete'">삭제</button></td>
						</tr>
					</tbody>
				</table>
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