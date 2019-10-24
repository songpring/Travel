<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>후기작성 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->

<style type="text/css">
.row-1 {
	margin-left: -15px;
	margin-right: -15px;
}

.recentlist {
	float: left;
	margin: 50px 0 50px 10px;
}

.hotlist {
	float: left;
	margin: 50px 10px 50px 0;
}
</style>


<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<script>
$(document).ready(function() {
	  $('#summernote').summernote({
 	    	minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});
</script>

</head>
<body>
	<div class="colorlib-loader"></div>
	<div id="page">
		<nav class="colorlib-nav" role="navigation">
			<!-- 탑메뉴 들어가는 곳 -->
			<jsp:include page="../inc/top.jsp" />
			<!-- 탑메뉴 들어가는 곳 -->

		</nav>
		<aside id="colorlib-hero">
			<div class="flexslider">
				<ul class="slides">
					<li
						style="background-image: url(https://images.pexels.com/photos/1000443/pexels-photo-1000443.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
						<div class="overlay"></div>
						<div class="container-fluid">
							<div class="row-1">
								<div class="col-md-6 col-sm-12 col-md-offset-3 slider-text">
									<div class="slider-text-inner text-center">
										<h2>리얼트래블러의</h2>
										<h1>여행후기</h1>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</aside>

		<div class="colorlib-wrap">
			<div class="container1">
				<div class="row">
					<div class="container">
						<div class="about-flex">
							<table style="margin: 0 auto;">
								<tr>
									<td>
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
										</table>
										<form action='<c:url value="/reviewWrite"/>' method="post">
											<table class="table">
												<tbody>
													<tr>
														
														<th scope="row"></th>
														<td colspan="2"></td>
													</tr>
													<tr>
														<th scope="row">여행지분류</th>
														<td colspan="2"><select name="country">
																<option value="유럽">유럽</option>
																<option value="아시아">아시아</option>
																<option value="중동">중동</option>
																<option value="북아메리카">북아메리카</option>
																<option value="남아메리카">남아메리카</option>
																<option value="오세아니아">오세아니아</option>
																<option value="아프리카">아프리카</option>
														</select></td>
														<th scope="row"></th>
														<td colspan="2"></td>
													</tr>
													<tr>
														<th scope="row">작성자</th>
														<td colspan="5">${mb.nickname }</td>
													</tr>
													<tr>
														<th scope="row">제목</th>
														<td colspan="5" width="90%"><input type="text"
															name="subject"style="width: 100%;" placeholder="  제목을 적어주세요"></td>
													</tr>
													
													<tr>
														<td colspan="6">
															<textarea id="summernote" name="content" >
															예시 형식 ) <br>나라 : <br>	항공사 : <br>  숙소 : <br> 기간 : <br> 비용 : <br> 갔던 곳 : <br> 제일 좋았던 곳 : <br> 먹은 음식 : <br>	제일 맛있었던 음식 : <br>	별점 :</textarea>
														</td>
													</tr>
												</tbody>
											</table>
											<input class="btn btn-primary" type="submit" value="등록" style="float: right;">
										</form>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="gototop js-top">
			<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
		</div>
	</div>
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp" />

</body>
</html>