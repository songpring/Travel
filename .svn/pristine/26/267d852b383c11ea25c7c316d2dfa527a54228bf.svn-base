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


<!-- include libraries(jQuery, bootstrap) -->
<!-- <link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> -->

<!-- include summernote css/js-->
<link	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"	rel="stylesheet">
<script 	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>



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
						
							<!-- 							<h2>공항</h2> -->
							<div class="row">
								<div class="col-md-12">
									<table>
										<tr>
											<td>
												<table width="100%" cellpadding="0" cellspacing="0"
													border="0">
												</table>
												<form action='<c:url value="/honeyWrite"/>' method="post"
													enctype="multipart/form-data">
													<table class="table">
														<tbody>
															<tr>
																
																<th scope="row">작성자</th>
																<td colspan="2">${mb.nickname }<input type="hidden"
																	name="id" value=${id }></td>
															</tr>
															<tr>
																<th scope="row">분류</th>
																<td colspan="2"><select name="board">
																		<option value="공항">공항</option>
																		<option value="교통">교통</option>
																		<option value="날씨">날씨</option>
																		<option value="문화">문화</option>
																</select></td>
																<th scope="row"></th>
																<td colspan="2"></td>
															</tr>
															<tr>
																<th scope="row">제목</th>
																<td colspan="5" width="90%"><input type="text"
																	name="subject"  style="width: 100%;"></td>
															</tr>
															
															<tr>
																<td colspan="6"><textarea  rows="5" class="form-control" id="summernote" name="content"></textarea></td>
															</tr>
														</tbody>
													</table>

													<script>
														$(document).ready(function() {
																			$('#summernote').summernote({
																				placeholder : '내용을 적어주세요~ 내용적으면 사라집니당~',
																				minHeight: 370,
																		        maxHeight: null,
																				focus : true,
																				lang : 'ko-KR'
																			});
																		});
													</script>


													<input class="btn btn-primary" type="submit" value="등록"
														style="float: right;">
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

