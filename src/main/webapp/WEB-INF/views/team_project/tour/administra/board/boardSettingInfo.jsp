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
	$("#btnSave").click(function () { Save(); return false; });
	$("#btnDelete").click(function () { Delete(); return false; });
	
	$("#btnInin").click(function () { Init(); return false; });
	$("#btnList").click(function () { List(); return false; });
	
	$("#btnColumnInfo").click(function () { ColumnInfo(); return false; });
	
	
	
	// 저장
	var Save = function(pDepe) {
		$.confirm({
		    title: '확인',
		    content: '저장하시겠습니까?',
		    buttons: {
		    	저장: function () {
		    		$('#fr').submit();
		    	},
		               취소: function () {
		            Toastr("info","취소 하였습니다.");
		       }
		    }
		});
	};
	
	// 삭제
	var Delete = function(pDepe) {
		$.confirm({
		    title: '확인',
		    content: '삭제하시겠습니까?',
		    buttons: {
		    	삭제: function () {
		    		$("#fr").attr("action", '<c:url value="/adminBoardSettingDelete"/>');
		    		$('#fr').submit();
		    	},
		               취소: function () {
		            Toastr("info","취소 하였습니다.");
		       }
		    }
		});
	};
	
	// 리스트
	var List = function() {
		location.replace('<c:url value="/adminBoardSetting"/>');
	}
	
	// 추가(초기화)
	var Init = function() {
		var pTarget = "#divInfo";
		$(pTarget).find("input:text").val("");
        $(pTarget).find("textarea").val("");
        $(pTarget).find("select").val("");
        $(pTarget).find("input:checkbox").prop("checked", true);
        $(pTarget).find("input:radio").eq(0).prop("checked", true);
        $("#board_id").attr("readonly", false);
        $("#seq").val("0");
	}
	
	// 컬럼 세팅
	var ColumnInfo = function () {
		$("#fr").attr("action", '<c:url value="/adminTableColumnMappingInfo"/>');
		$('#fr').submit();
	}
	
	// 셀렉트 이벤트
	
	// 글쓰기 권한	
	$("select[id$=ssl_writing_authority]").change(function () {
    	if($("select[id$=ssl_writing_authority]").val()!=""){
    		if($("#writing_authority").val()==""){
    			$("#writing_authority").val($("select[id$=ssl_writing_authority]").val());
    		}
    		else{
    			$("#writing_authority").val($("#writing_authority").val()+","+$("select[id$=ssl_writing_authority]").val());
    		}
    	}
    });
	
	// 글수정 권한
	$("select[id$=ssl_modification_authority]").change(function () {
    	if($("select[id$=ssl_modification_authority]").val()!=""){
    		if($("#modification_authority").val()==""){
    			$("#modification_authority").val($("select[id$=ssl_modification_authority]").val());
    		}
    		else{
    			$("#modification_authority").val($("#modification_authority").val()+","+$("select[id$=ssl_modification_authority]").val());
    		}
    	}
    });
	// 글삭제 권한
	$("select[id$=ssl_delete_authority]").change(function () {
    	if($("select[id$=ssl_delete_authority]").val()!=""){
    		if($("#delete_authority").val()==""){
    			$("#delete_authority").val($("select[id$=ssl_delete_authority]").val());
    		}
    		else{
    			$("#delete_authority").val($("#delete_authority").val()+","+$("select[id$=ssl_delete_authority]").val());
    		}
    	}
    });
	// 글읽기 권한
	$("select[id$=ssl_read_authority]").change(function () {
    	if($("select[id$=ssl_read_authority]").val()!=""){
    		if($("#read_authority").val()==""){
    			$("#read_authority").val($("select[id$=ssl_read_authority]").val());
    		}
    		else{
    			$("#read_authority").val($("#read_authority").val()+","+$("select[id$=ssl_read_authority]").val());
    		}
    	}
    });
	
	
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
						<c:choose>
							<c:when test="${bsb.seq==0}">
								<h3>게시판 추가</h3>
							</c:when>
							<c:otherwise>
								<h3>게시판 설정 정보</h3>
							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="panel panel-white">
							<form action='<c:url value="/adminBoardSettingUpdate"/>' method="POST" name="fr" id="fr" >
								<div id="divInfo" class="panel-body padding-10">
									<div>
										<table class="table-grid responsive-table margin-bottom-8">
											<tr>
												<th><label for="details-code-name">번호</label></th>
												<td>
													<div class="form-inline">
														<input id="seq" name="seq" class="form-control" type="text" value="${bsb.seq}" readonly="readonly">
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">게시판 아이디 </label></th>
												<td>
													<div class="form-inline">
														<c:choose>
															<c:when test="${bsb.seq==0}">
																<input id="board_id" name="board_id" class="form-control" type="text" value="${bsb.board_id}">
															</c:when>
															<c:otherwise>
																<input id="board_id" name="board_id" class="form-control" type="text" value="${bsb.board_id}" readonly="readonly">
															</c:otherwise>
														</c:choose>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">게시판 이름</label></th>
												<td>
													<div class="form-inline">
														<label><input id="board_name" name="board_name" class="form-control" type="text" value="${bsb.board_name}"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">게시판 종류</label></th>
												<td>
													<div class="form-inline">
														<label>${board_types}</label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">글쓰기 권한</label></th>
												<td>
													<div class="form-inline">
														<div class="form-group">
															<input id="writing_authority" name="writing_authority" class="form-control" type="text" value="${bsb.writing_authority}">
														</div>
														<div class="form-group">
															<label>${ssl_writing_authority}</label>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">글수정 권한</label></th>
												<td>
													<div class="form-inline">
														<div class="form-group">
															<input id="modification_authority" name="modification_authority" class="form-control" type="text" value="${bsb.modification_authority}">
														</div>
														<div class="form-group">
															<label>${ssl_modification_authority}</label>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">글삭제 권한</label></th>
												<td>
													<div class="form-inline">
														<div class="form-group">
															<input id="delete_authority" name="delete_authority" class="form-control" type="text" value="${bsb.delete_authority}">
														</div>
														<div class="form-group">
															<label>${ssl_delete_authority}</label>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">글읽기 권한</label></th>
												<td>
													<div class="form-inline">
														<div class="form-group">
															<input id="read_authority" name="read_authority" class="form-control" type="text" value="${bsb.read_authority}">
														</div>
														<div class="form-group">
															<label>${ssl_read_authority}</label>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">페이지당 출력 갯수</label></th>
												<td>
													<div class="form-inline">
														<input id="page_size" name="page_size" class="form-control num_only" type="text" value="${bsb.page_size}">
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">페이징 갯수</label></th>
												<td>
													<div class="form-inline">
														<input id="pages_size" name="pages_size" class="form-control num_only" type="text" value="${bsb.pages_size}">
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">공지사항 여부</label></th>
												<td>
													<div class="form-inline">
														<label>${notification_use}</label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">첨부파일 여부</label></th>
												<td>
													<div class="form-inline">
														<label>${file_use}</label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">답변사용 여부</label></th>
												<td>
													<div class="form-inline">
														<label>${re_write_use}</label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코멘트 사용 여부</label></th>
												<td>
													<div class="form-inline">
														<label>${comments_use}</label>
													</div>
												</td>
											</tr>
										</table>
									</div>
									<div class="panel-heading border-bottom padding-10 clearfix">
										<div class="pull-left match-height">
											<h4 class="panel-title v-center">
	<!-- 											<span aria-hidden="true" class="fa fa-info-circle"></span>상세정보 -->
											</h4>
										</div>
										
										<c:choose>
											<c:when test="${bsb.seq==0}">
												<div class="pull-right match-height" >
													<input type="button" name="btnSave" id="btnSave" value="저장" class="btn btn-primary btn-block">
												</div>
												<div class="pull-right match-height" >
													<input type="button" name="btnList" id="btnList" value="목록" class="btn btn-primary btn-block">
												</div>
											</c:when>
											<c:otherwise>
												<div class="pull-right match-height" >
													<input type="button" name="btnColumnInfo" id="btnColumnInfo" value="컬럼 세팅" class="btn btn-primary btn-block">
												</div>
												
												<div class="pull-right match-height" >
													<input type="button" name="btnDelete" id="btnDelete" value="삭제" class="btn btn-primary btn-block">
												</div>
												
												<div class="pull-right match-height" >
													<input type="button" name="btnSave" id="btnSave" value="저장" class="btn btn-primary btn-block">
												</div>
												<div class="pull-right match-height" >
													<input type="button" name="btnInin" id="btnInin" value="추가" class="btn btn-primary btn-block">
												</div>
												<div class="pull-right match-height" >
													<input type="button" name="btnList" id="btnList" value="목록" class="btn btn-primary btn-block">
												</div>
											</c:otherwise>
										</c:choose>
										
										
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

