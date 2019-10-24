<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>진짜 필요한 꿀~팁 - 위트래블</title>
<!-- header 들어가는 곳 -->
<jsp:include page="../../inc/intro.jsp" />
<!-- header 들어가는 곳 -->
<script src="//cdn.ckeditor.com/4.12.1/standard/ckeditor.js"></script>

<style type="text/css">
.buttons {
	float: right;
	margin-right: 20em;
	margin-top: 2em;
}
</style>
</head>
<body>

	<div class="colorlib-loader"></div>

	<div id="page">
		<nav class="colorlib-nav" role="navigation">
			<!-- 탑메뉴 들어가는 곳 -->
			<jsp:include page="../../inc/top.jsp" />
			<!-- 탑메뉴 들어가는 곳 -->
		</nav>
		<aside id="colorlib-hero">
			<div class="flexslider">
				<ul class="slides">
					<li
						style="background-image: url(https://images.pexels.com/photos/1252500/pexels-photo-1252500.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
						<div class="overlay"></div>
						<div class="container-fluid">
							<div class="row">
								<div
									class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
									<div class="slider-text-inner text-center">
										<h2>놓칠 수 없는</h2>
										<h1>꿀-팁</h1>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</aside>

		<div id="colorlib-about">
			<div class="container">
				<div class="row">
					<div class="about-flex" id="airport">
						<!-- 꿀팁공통메뉴 들어가는 곳 -->
							<jsp:include page="../../inc/honeySide.jsp" />
						<div class="col-three-forth animate-box">
							<h2>공항</h2>
							<div class="row">
								<div class="col-md-12">
									<table>
										<tr>
											<td>
												<table width="100%" cellpadding="0" cellspacing="0"
													border="0">
												</table>
												<form action='<c:url value="/honeyUpdate?seq=${bb1.seq }"/>' method="post" id="soojeong">
												<table class="table">
													  <tbody>
													    <tr>
													      <th scope="row">No</th>
													      <td colspan="2">${bb1.seq }</td>
													       <th scope="row">조회수</th>
													      <td colspan="2">${bb1.readcount }</td>
													    </tr>
													    <tr>
													      <th scope="row">작성자</th>
													      <td colspan="2">${bb1.regi_id }</td>
													      <th scope="row">추천수</th>
													      <td colspan="2">${bb1.recommend}</td>
													    </tr>
													    <tr>
													      <th scope="row">제목</th>
													      <td colspan="5" width=""><input type="text" name="subject" value="${bb1.subject }"></td>
													    </tr>
													    <tr>
													      <th scope="row" colspan="2">첨부파일</th>
													      <td colspan="4">Larry.jpg</td>
													    </tr>
													    <tr>
													      <td colspan="6">
													      <textarea rows="5" class="form-control" name="content">${bb1.content }
															</textarea> 
															<script>
																CKEDITOR.replace(
																				"content",
																				{
																					width : '100%',
																					height : '500px'
		
																				});
															</script>
													      </td>
													    </tr>
													  </tbody>
												</table>
												<input type="submit" value="글수정" >
												</form>
											</td>
										</tr>
									</table>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- 이메일구독 들어가는 곳 -->
	<jsp:include page="../../inc/email.jsp" />

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/bottom.jsp" />


</body>
</html>

