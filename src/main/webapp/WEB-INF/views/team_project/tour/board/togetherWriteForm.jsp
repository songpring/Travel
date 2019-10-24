<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>동행</title>
<!-- header 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- header 들어가는 곳 -->
<style type="text/css">
.buttons {
	float: right;
	margin-right: 20em;
	margin-top: 2em;
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

<script src='<c:url value="/resources/assets/tour/js/common.js"/>'></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		
		var City_dep = function () {
	        //var params = JSON.parse("{}");
	        var params = {};
	        params["control_type"] ="select2"; //radio ,select ,select2, check
	     	params["first_item_type"]="select"; // select:선택 , all:전체
	     	params["code_div"]="CT";
	     	params["type"]="code";
	     	params["name"]="dep";
	     	params["use_method1"]=$("select[id$=selCountry_dep]").val();
	        LoadCommonCode(null, params, false, function (pResult) {
	        	
	        	console.log(pResult);
	        	var data = jQuery.parseJSON(pResult);
	             //셀렉트 박스 리셋 필요하 따라 리셋을 않해도 됩
	             $("select[id$=selCity_dep] option").remove();
	              console.log(data);
	             //데이터 바인딩
	             $("select[id$=selCity_dep]").append("<option value=''>선택</option>");
	             for (var index in data.Table) {
	                 $("select[id$=selCity_dep]").append("<option value='" + data.Table[index]["VALUE"] + "' style='color: gray;'>" + data.Table[index]["TEXT"] + "</option>");
	             }
	        }, false);
	    };

		//국가 변경
	    $("select[id$=selCountry_dep]").change(function () {
	    	if($("select[id$=selCountry_dep]").val()!=""){
	    		//도시 호출
	        	City_dep();	
	    	}
	    	else{
	    		$("select[id$=selCity_dep]").val("");
	    	}
	    });
		
	    
	    var params = {};
	 	params["type"] ="Scd";
	 	params["call_type"]="AllDate";
	 	params["code"] ="01";
	 	params["code_div"] ="01";
	 	Ajax(params, function(pResult) {
	 		 console.log(pResult);
		},false);
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
					style="background-image: url(https://images.pexels.com/photos/1252500/pexels-photo-1252500.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h2>놓칠 수 없는</h2>
									<h1>동 - 행</h1>
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
									<form action='<c:url value="/togetherWrite"/>' method="post">
										<table class="table">
											<tbody>
												<tr>
											<th scope="row">나라</th>
											<td colspan="4">${selCountry_dep}</td>
											<th scope="row"></th>
										</tr>
										<tr>
											<th scope="row">도시</th>
											<td colspan="4">${selCity_dep}</td>
											<th scope="row"></th>
										</tr>
									    <tr>
									    	<th scope="row">제목</th>
									    	<td colspan="5" width="95%"><input type="text" name="subject" required="required" style="width: 100%;" placeholder="  제목을 적어주세요"></td>
									    </tr>
									    <tr>
									      <td colspan="6">
									      	<textarea id="summernote" name="content" style="width: 70%">*필수 입력사항 <br>이름 : <br> 나이 : <br> 성별 : <br> 휴대폰번호 : <br> 출발 장소 : <br> 나라 : <br> 인원 수 :</textarea>
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

<!-- 이메일구독 들어가는 곳 -->
<jsp:include page="../inc/email.jsp" />

<div class="gototop js-top">
	<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
</div>

<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />


</body>
</html>

