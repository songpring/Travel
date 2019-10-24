<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="../../inc/intro.jsp" />
<title>게시판 설정</title>
<script src='<c:url value="/resources/assets/tour/js/common.js"/>'></script>

<!-- 그리드용 -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/jquery-ui.min.css" /> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/ui.jqgrid.css" /> -->
<!-- <script src="./resources/assets/tour/jqGrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script> -->
<!-- <script src="./resources/assets/tour/jqGrid/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script> -->

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>


<script type="text/javascript">
$(document).ready(function () {
	$("#btnSetting").click(function () { btnSetting(); return false; });
	
	$(".btnClick").on("click", function(e){ 
  		var data = $(this).parent().parent();
		$.confirm({
		    title: '확인',
		    content: '저장하시겠습니까?',
		    buttons: {
		    	저장: function () {
		    	    var params = {};
// 			console.log($(this));
		    	    
// 		    	    console.log(data);
		    	    
		    	    $.each(data,function(){
		    	    	if($(this).find('input[name="seq"]').val()!=""){
		    	    		params["seq"] = $(this).find('input[name="seq"]').val()
		    	    	}
		    	    	if($(this).find('input[name="board_id"]').val()!=""){
		    	    		params["board_id"] = $(this).find('input[name="board_id"]').val()
		    	    	}
		    	    	if($(this).find('input[name="column_code"]').val()!=""){
		    	    		params["column_code"] = $(this).find('input[name="column_code"]').val()
		    	    	}
		    	    	if($(this).find('input[name="board_name_kr"]').val()!=""){
		    	    		params["board_name_kr"] = $(this).find('input[name="board_name_kr"]').val()
		    	    	}
		    	    	if($(this).find('select[name="column_type"]').val()!=""){
		    	    		params["column_type"] = $(this).find('select[name="column_type"]').val()
		    	    	}
		    	    	if($(this).find('textarea[name="information"]').val()!=""){
		    	    		params["information"] = $(this).find('textarea[name="information"]').val()
		    	    	}
		    	    	if($(this).find('select[name="board_column_use"]').val()!=""){
		    	    		params["board_column_use"] = $(this).find('select[name="board_column_use"]').val()
		    	    	}
		    	    });
		    	    
		    		params["type"] = "tableColumn";
		    		params["call_type"] = "save";
		    		//console.log(params);
		    		//return false;
		    		Ajax(params, function(pResult) {
		    	 		//console.log(pResult);
		    	 		if (pResult.result != null) {
		    	 			Toastr(pResult.data,pResult.message);
		    	 			//Search(pDepe);
		    			}
		    		},false);
		    	},
		               취소: function () {
		            Toastr("info","취소 하였습니다.");
		       }
		    }
		});
	    
	    
	});
	// 리스트
	var btnSetting = function() {
		var $form = $('<form></form>');
        $form.attr('action', '<c:url value="/adminBoardSettingInfo"/>');
        $form.attr('method', 'post');
        $form.appendTo('body');
        var seq = $('<input type="hidden" value="'+${seq}+'" name="seq">');
        $form.append(seq);
        $form.submit();
	}
	
});

</script>
</head>
<body>
	<div class="colorlib-loader"></div>
	<div id="page">
		<nav class="colorlib-nav" role="navigation">
			<!-- 탑메뉴 들어가는 곳 -->
			<jsp:include page="../../inc/adminTop.jsp" />
			<!-- 탑메뉴 들어가는 곳 -->
		</nav>
		<aside id="colorlib-hero">
			<div class="flexslider">
				<ul class="slides">
					<li style="background-image: url(./resources/assets/tour/images/cover-img-3.jpg);">
						<div class="overlay"></div>
						<div class="container-fluid"></div>
					</li>
				</ul>
			</div>
		</aside>
		<div id="colorlib-testimony" class="colorlib-light-grey">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 text-center colorlib-heading animate-box">
						<h3>게시판 컬럼 설정 정보</h3>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="panel panel-white">
							<form action='<c:url value="/adminBoardSettingUpdate"/>' method="POST" name="fr" id="fr" >
								<div id="divInfo" class="panel-body padding-10">
									<div>
										<table class="table table-hover margin-bottom-8">
											<thead>
												<tr class="table-info">
													<th width="7%">번호</th>
										            <th width="10%">아이디</th>
										            <th width="10%">컬럼코드</th>
										            <th width="20%">컬럼한글명</th>
										            <th width="15%">컬럼타입</th>
										            <th width="25%">안내내용</th>
										            <th width="10%">사용 여부</th>
										            <th width=""></th>
					          					</tr>
				          					</thead>
											<tbody>
												<c:forEach var="bean" items="${list }">
													<tr>
														<td>
															<input name="seq" class="form-control" type="text" value="${bean.seq}" readonly="readonly">
														</td>
														<td>
															<input name="board_id" class="form-control" type="text" value="${bean.board_id}" readonly="readonly">
														</td>
														<td><input name="column_code" class="form-control" type="text" value="${bean.column_code}" readonly="readonly">
														</td>
														<td><input name="board_name_kr" class="form-control" type="text" value="${bean.board_name_kr}"></td>
														<td>
															${bean.column_type}
														</td>
														<td><textarea name="information" rows="2" cols="" class="form-control" style="resize: none;">${bean.information }</textarea>
														
														</td>
														<td>${bean.board_column_use }</td>
														<td><input type="button" value="저장" class="btn btn-primary btn-block btnClick"></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										
									</div>
									<div class="panel-heading border-bottom padding-10 clearfix">
										<div class="pull-left match-height">
											<h4 class="panel-title v-center">
	<!-- 											<span aria-hidden="true" class="fa fa-info-circle"></span>상세정보 -->
											</h4>
										</div>
										<div class="pull-right match-height" >
											<input type="button" name="btnSetting" id="btnSetting" value="계시판 설정" class="btn btn-primary btn-block">
										</div>
									</div>
								</div>
							</form>
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
	<%-- 	<jsp:include page="../../inc/bottom.jsp" /> --%>



</body>
</html>

