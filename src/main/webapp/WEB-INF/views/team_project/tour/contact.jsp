<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE HTML>
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>위트레블 고객센터</title>
<!-- head 들어가는 곳 -->
<jsp:include page="inc/intro.jsp" />
<!-- head 들어가는 곳 -->

	</head>
	<body>
		
	<div class="colorlib-loader"></div>

	<div id="page">
		<nav class="colorlib-nav" role="navigation">
			<!-- 탑메뉴 들어가는 곳 -->
			<jsp:include page="inc/top.jsp" />
			<!-- 탑메뉴 들어가는 곳 -->
		</nav>
		<aside id="colorlib-hero">
			<div class="flexslider">
				<ul class="slides">
			   	<li style="background-image: url(https://images.pexels.com/photos/2598620/pexels-photo-2598620.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260);">
			   		<div class="overlay"></div>
			   		<div class="container-fluid">
			   			<div class="row">
				   			<div class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
				   				<div class="slider-text-inner text-center">
				   					<h2>WeTravel</h2>
				   					<h1>고객센터</h1>
				   				</div>
				   			</div>
				   		</div>
			   		</div>
			   	</li>
			  	</ul>
		  	</div>
		</aside>

		<div id="colorlib-contact">
			<div class="container">
				<div class="row">
					<div class="col-md-10 col-md-offset-1 animate-box">
						<h3>이메일문의</h3>
						<form action='<c:url value="/mailSend"/>' method="post">
							<div class="row form-group">
								<div class="col-md-6 padding-bottom">
									<label for="fname">이름</label>
									<input type="text" id="fname" name="name" class="form-control" autocomplete="off">
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-12">
									<label for="email">이메일</label>
									<input type="text" name="tomail" id="email" class="form-control" autocomplete="off">
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-12">
									<label for="subject">제목</label>
									<input type="text" name="title" id="subject" class="form-control" autocomplete="off">
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-12">
									<label for="message">문의내용</label>
									<textarea  autocomplete="off" name="content" id="message" cols="30" rows="10" class="form-control" placeholder="입력해주세요."
									style="resize: none;"></textarea>
								</div>
							</div>
							<div class="form-group text-center">
								<input type="hidden" value="tourcontact" name="url">
								<input type="submit" value="보내기" class="btn btn-primary">
							</div>

						</form>		
					</div>
					<div class="col-md-10 col-md-offset-1 animate-box">
						<h3>오피스</h3>
						<div class="row contact-info-wrap">
							<div class="col-md-3">
								<p><span><i class="icon-location"></i></span> 남대문, 서울시</p>
							</div>
							<div class="col-md-3">
								<p><span><i class="icon-phone3"></i></span> <a href="tel://1234567920">+82 2 845 4415</a></p>
							</div>
							<div class="col-md-3">
								<p><span><i class="icon-paperplane"></i></span> <a href="mailto:info@yoursite.com">info@wetravel.com</a></p>
							</div>
							<div class="col-md-3">
								<p><span><i class="icon-globe"></i></span> <a href="#">itwillbs7.cafe24.com</a></p>
							</div>
						</div>
						<div id="map" style="width:700px;height:400px; margin: 0 auto;"></div>
								<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8f5686e35f1ef7ab73e278e16d61b449"></script>
								<script>
									var container = document.getElementById('map');
									var options = {
										center: new kakao.maps.LatLng(37.561333, 126.973075),
										level: 3
									};
							
									var map = new kakao.maps.Map(container, options);
									

									// 마커가 표시될 위치입니다 
									var markerPosition  = new kakao.maps.LatLng(37.561333, 126.973075); 

									// 마커를 생성합니다
									var marker = new kakao.maps.Marker({
									    position: markerPosition
									});

									// 마커가 지도 위에 표시되도록 설정합니다
									marker.setMap(map);
									
								</script>
					</div>
				</div>
			</div>
		</div>

		<div id="map" class="colorlib-map"></div>
	
		
		
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>
	
<!-- 푸터 들어가는 곳 -->
	<jsp:include page="inc/bottom.jsp" />


	</body>
</html>

