<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>마이 페이지- 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="inc/intro.jsp" />
<!-- head 들어가는 곳 -->

</head>
<body>
	<div class="colorlib-loader"></div>
	<div id="page"></div>
	<nav class="colorlib-nav" role="navigation">
		<!-- 탑메뉴 들어가는 곳 -->
		<jsp:include page="inc/top.jsp" />
		<!-- 탑메뉴 들어가는 곳 -->
	</nav>
	<aside style="  height: 150px;
  background-color: gray ;
  width: 100%;
  float: left;
  z-index: 0; ">
		<div class="flexslider">
			<ul class="slides">
				<li style="background-color: green ;">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								
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
			<div align="center">
<!-- 				구독을 취소하시겠습니까? <br> -->
<!-- 				<input type="button" id="cancel" value="구독취소">    -->
<%-- 				<input type="button" value="메인으로" onclick="location.href='<c:url value="/tourindex"/>'"> --%>
			</div>
			</div>
		</div>
	</article>
	<script type="text/javascript">
// 		$("#cancel").click(function(){
			
// 			alert("구독취소 되었습니다.");
// 			location.href='<c:url value="/tourindex"/>';
// 		});

	
	</script>
	
	<!-- 맨위로 가게하는 버튼 -->
	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="inc/bottom.jsp" />
</body>
</html>
