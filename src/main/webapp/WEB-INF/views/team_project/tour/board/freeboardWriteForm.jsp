<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->

<!-- include libraries(jQuery, bootstrap) -->
<!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>  -->

<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<title>글쓰기</title>

<script>
$(document).ready(function() {
	  $('#summernote').summernote({
 	    	placeholder: '내용을 적어주세요~ 내용적으면 사라집니당~',
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
		   	<li style="background-image: url(https://images.pexels.com/photos/2873686/pexels-photo-2873686.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
		   		<div class="overlay"></div>
		   		<div class="container-fluid">
		   			<div class="row-1">
			   			<div class="col-md-6 col-sm-12 col-md-offset-3 slider-text">
			   				<div class="slider-text-inner text-center">
			   					<h2>자유롭게 대화하는</h2>
			   					<h1>자유게시판</h1>
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
									<form action='<c:url value="/board/freeboardWrite"/>' method="post">
										<table class="table">
											<tbody>
												<tr>
													<c:if test='${sessionScope.id == "admin" }'>
													<th scope="row">공지여부</th>
													<td colspan="4">
													
                   							
              							 				<select id="notice_use" name="notice_use" >
															<option id="N" value="N" >일반</option>
															<option id="Y" value="Y" >공지</option>
														</select>
													</td>
													</c:if>
              								
              								
												</tr>
												<tr>
													<th scope="row">제목</th>
													<td colspan="5" width="100%"><input type="text" name="subject" style="width: 100%;" placeholder="제목을 적어주세요"/></td>
												</tr>
												<tr>
												  <td colspan="6">
													<textarea id="summernote" name="content" ></textarea>
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
</div>
<div class="gototop js-top">
	<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
</div>

	<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />

</body>
</html>