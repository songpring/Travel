<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String id=(String)session.getAttribute("id");
if(id == null){
	response.sendRedirect("tourindex");
}

%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>갤러리 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->
<style type="text/css">
table tr, td {
	padding: 0.5em;
}
</style>
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
						style="background-image: url(https://images.pexels.com/photos/2325446/pexels-photo-2325446.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
						<div class="overlay"></div>
						<div class="container-fluid">
							<div class="row">
								<div
									class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
									<div class="slider-text-inner text-center">
										<h2>마치 여행지에 와있는 듯한</h2>
										<h1>갤러리</h1>
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
						<h2 style="text-align: center;">이미지 업로드</h2>
						<p style="text-align: center; margin-bottom: 5em;">여행의 순간들을 여러사람들과 공유해보세요!</p>
						<form class="form-horizontal" name="form" runat="server" action='<c:url value="imageUpload"/>' method="post" enctype="multipart/form-data">
							<div class="form-group form-group-sm">
								<label class="col-sm-2 control-label" for="inputdefault">해시태크#</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" id="sm" name="hashtags" autocomplete="off">
									<span class="help-block">띄어쓰기 없이 쉼표(,)로만 구분</span>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="col-sm-2 control-label" for="inputdefault">본문</label>
								<div class="col-sm-10">
<!-- 									<input class="form-control" type="text" id="sm" maxlength="100" -->
<!-- 										name="content"> -->
									<textarea id="form7" autocomplete="off" class="md-textarea form-control" rows="3" maxlength="300" name="content"
									onkeyup="doCheck()" style="resize: none;"></textarea>
									<span class="help-block"><input type="text" name="sofar" readonly style="border: none; outline: 0; text-align: right;" size="3" placeholder="300">자 남음</span>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="col-sm-2 control-label" for="inputdefault">파일선택</label>
								<div class="col-sm-10">
									<input type='file' accept=".jpg, .png, .jpeg" id="imgInput" name="file" sizes="600px"/>
									<span class="help-block">jpg, png만 가능</span>
								</div>
							</div>
							<div class="form-group form-group-sm">
									<label class="col-sm-2 control-label" for="inputdefault">미리보기</label>
									<div class="col-sm-10" style="width: 500px;height: 100%;">
										<img id="image_section"
											name="" src="" 
											style="margin: 0 auto; width: 100%;" />
									</div>
									
									
							</div>
							<!-- 히든으로 값 들고감 -->
							<input type="hidden" name="secret_use" value="N">
							<input type="hidden" name="notice_use" value="N">
							<input type="hidden" name="id" value="${id }">
							<input type="submit" value="등록하기"  class="btn btn-primary"
								style="float: right; margin-top: 1em;">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 이미지 업로드 -->
	<script type="text/javascript">
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#image_section').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("#imgInput").change(function() {
			readURL(this);
		});
	</script>
	<!-- 내용 글자수 출력 -->
	<script type="text/javascript">
	
	var total = 300;
	function doCheck(){
		var check = document.form.content.value.length;
		var sofar = total - check;
		document.form.sofar.value = sofar;
	}
	
	</script>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp" />
</body>
</html>

