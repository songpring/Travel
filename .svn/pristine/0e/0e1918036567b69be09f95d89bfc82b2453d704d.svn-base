<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../inc/intro.jsp" />
<title>공통코드</title>
<link rel="stylesheet" href="./resources/assets/tour/css/fixed-table.css">
<script src="./resources/assets/tour/js/common.js"></script>

<!-- 그리드용 -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/ui.jqgrid.css" />
<script src="./resources/assets/tour/jqGrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="./resources/assets/tour/jqGrid/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>

<script type="text/javascript">
  
$(document).ready(function () {
	// 아작스로 데이터 가져 오기
	
	// 1차 분류 코드 검색 버튼
	$("#btnSearch1").click(function () { Search("1"); return false; });
	// 2차 분류 코드 검색 버튼
	$("#btnSearch2").click(function () { Search("2"); return false; });
	
	// 초기화
	$("#btnInin1").click(function () { Init("1"); return false; });
	$("#btnInin2").click(function () { Init("2"); return false; });
	// 저장
	$("#btnSave1").click(function () { Save("1"); return false; });
	$("#btnSave2").click(function () { Save("2"); return false; });
	// 삭제
	$("#btnDelete1").click(function () { Delete("1"); return false; });
	$("#btnDelete2").click(function () { Delete("2"); return false; });
	// 중복확인
	//$("#btnDelete1").click(function () { Delete("1"); return false; });
	//$("#btnDelete2").click(function () { Delete("2"); return false; });

	
	// 검색
	var Search = function(pDepe) {
		var params = {};
	 	params["type"] ="Scd";
	 	params["call_type"]="AllDate";
	 	if(pDepe=="1"){
	 		params["code_step"] ="1";	
	 	}
	 	else{
	 		params["code_step"] ="2";
	 		params["code_div"] = $("#code_1").val();
	 	}
	 	params["code_name"] = $("#code_name"+pDepe).val();
	 	//console.log(params);
	 	AjaxList(params, function(pResult) {
	 		//console.log(pResult.length);
	 		Init(pDepe);
	 		if(pDepe=="1"){
	 			//console.log("여기 오냐");
	 			Init("2");
	 			$("#list2").clearGridData();
	 			$("#spCount"+pDepe).html("");
	 		}
	 		$("#spCount"+pDepe).html(pResult.length);
	 		$("#list"+pDepe).clearGridData();
	 		$("#list"+pDepe).jqGrid('setGridParam',{data:pResult});
	 		$("#list"+pDepe).trigger('reloadGrid');
		},false);
	};

	
	// 저장
	var Save = function(pDepe) {
		$.confirm({
		    title: '확인',
		    content: '저장하시겠습니까?',
		    buttons: {
		    	저장: function () {
		    		//Toastr("info","sssss");
		    		var params = getData(pDepe);
		    		params["type"] = "Scd";
		    		params["call_type"] = "save";
		    		//console.log(params);
		    		Ajax(params, function(pResult) {
		    	 		//console.log(pResult);
		    	 		if (pResult.result != null) {
		    	 			Toastr(pResult.data,pResult.message);
		    	 			Search(pDepe);
						}
		    		},false);
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
		    		//Toastr("info","sssss");
		    		var params = getData(pDepe);
		    		params["type"] = "Scd";
		    		params["call_type"] = "delete";
		    		//console.log(params);
		    		Ajax(params, function(pResult) {
		    	 		//console.log(pResult);
		    	 		if (pResult.result != null) {
		    	 			Toastr(pResult.data,pResult.message);
		    	 			Search(pDepe);
						}
		    		},false);
		    	},
		               취소: function () {
		            Toastr("info","취소 하였습니다.");
		       }
		    }
		});
	};
	
 	// 그리드 세팅
	$("#list1").jqGrid({
		datatype: 'local'
		, data: ''
	    , colNames:['', '코드', '코드이름', '','5','6','정렬','8','9','10','11','12','사용여부','14']
		, colModel :[ 
		    {name:'code_div', index:'code_div', hidden:true}//	코드구분	varchar	12
			, {name:'code', index:'code', width:"20%"}//	코드	varchar	12
			, {name:'code_name', index:'code_name', width:"40%"}//	한글코드명	varchar	100
			, {name:'code_sml_name', index:'code_sml_name', hidden:true}//	한글코드약어명	varchar	100
			, {name:'eng_name', index:'eng_name', hidden:true}//	영문명	varchar	100
			, {name:'eng_sml_name', index:'eng_sml_name', hidden:true}//	영문약어명	varchar	100
			, {name:'order_seq', index:'order_seq', width:"20%"}//	정렬순서	int	
			, {name:'order_seq1', index:'order_seq1', hidden:true}//	정렬순서1	int	
			, {name:'use_method1', index:'use_method1', hidden:true}//	용도구분1	varchar	200
			, {name:'use_method2', index:'use_method2', hidden:true}//	용도구분2	varchar	200
			, {name:'use_method3', index:'use_method3', hidden:true}//	용도구분3	varchar	200
			, {name:'code_step', index:'code_step', hidden:true}//	코드순서	int	
			, {name:'use_yn', index:'use_yn', width:"20%"}//	사용여부	varchar	1
			, {name:'emark', index:'emark', hidden:true}//	비고	varchar	1000
		  ]
		, onSelectRow: function(rowid) {
			setData($("#list1").getRowData(rowid),"1");
			Search("2");
		}
		, autowidth: true
    });
	
	$("#list2").jqGrid({
		datatype: 'local'
		, data: ''
	    , colNames:['', '코드', '코드이름', '','5','6','정렬','8','9','10','11','12','사용여부','14']
		, colModel :[ 
		    {name:'code_div', index:'code_div', hidden:true}//	코드구분	varchar	12
			, {name:'code', index:'code', width:"20%"}//	코드	varchar	12
			, {name:'code_name', index:'code_name', width:"40%"}//	한글코드명	varchar	100
			, {name:'code_sml_name', index:'code_sml_name', hidden:true}//	한글코드약어명	varchar	100
			, {name:'eng_name', index:'eng_name', hidden:true}//	영문명	varchar	100
			, {name:'eng_sml_name', index:'eng_sml_name', hidden:true}//	영문약어명	varchar	100
			, {name:'order_seq', index:'order_seq', width:"20%"}//	정렬순서	int	
			, {name:'order_seq1', index:'order_seq1', hidden:true}//	정렬순서1	int	
			, {name:'use_method1', index:'use_method1', hidden:true}//	용도구분1	varchar	200
			, {name:'use_method2', index:'use_method2', hidden:true}//	용도구분2	varchar	200
			, {name:'use_method3', index:'use_method3', hidden:true}//	용도구분3	varchar	200
			, {name:'code_step', index:'code_step', hidden:true}//	코드순서	int	
			, {name:'use_yn', index:'use_yn', width:"20%"}//	사용여부	varchar	1
			, {name:'emark', index:'emark', hidden:true}//	비고	varchar	1000
		  ]
		, onSelectRow: function(rowid, status, e) {
			setData($("#list2").getRowData(rowid),"2");
		}
		, autowidth: true
      });
	
	// 초기화
	var Init = function(pDepe) {
		var pTarget = "#divInfo"+pDepe;
		
		$(pTarget).find("input:text").val("");
        $(pTarget).find("textarea").val("");
        $(pTarget).find("select").val("");
        $(pTarget).find("input:checkbox").prop("checked", true);
        $(pTarget).find("input:radio").eq(0).prop("checked", true);
	}
	
	// 데이터 세팅
	var setData = function(pData, pDepe){
		Init(pDepe);
		$("#code_"+pDepe).val(pData.code);
		$("#code_name_"+pDepe).val(pData.code_name);
		$("#code_sml_name_"+pDepe).val(pData.code_sml_name);
		$("#eng_name_"+pDepe).val(pData.eng_name);
		$("#eng_sml_name_"+pDepe).val(pData.eng_sml_name);
		$("#use_method1_"+pDepe).val(pData.use_method1);
		$("#use_method2_"+pDepe).val(pData.use_method2);
		$("#use_method3_"+pDepe).val(pData.use_method3);
		$("#order_seq_"+pDepe).val(pData.order_seq);
		$("#order_seq1_"+pDepe).val(pData.order_seq1);
		$("#remark_"+pDepe).val(pData.remark);
		//console.log(pData.use_yn);
		if(pData.use_yn=="Y"){
			$("input:checkbox[id='use_yn_"+pDepe+"']").prop("checked", true);
		}
	};
	
	// 데이터 가져오기
	var getData = function(pDepe){
		var params = {}
		
		if(pDepe=="1"){
			params["code_div"] = "00";
		}
		else{
			params["code_div"] = $("#code_1").val();
		}
		params["code"] =$("#code_"+pDepe).val();
		params["code_name"] =$("#code_name_"+pDepe).val();
		params["code_sml_name"] =$("#code_sml_name_"+pDepe).val();
		params["eng_name"] =$("#eng_name_"+pDepe).val();
		params["eng_sml_name"] =$("#eng_sml_name_"+pDepe).val();
		params["use_method1"] =$("#use_method1_"+pDepe).val();
		params["use_method2"] =$("#use_method2_"+pDepe).val();
		params["use_method3"] =$("#use_method3_"+pDepe).val();
		params["order_seq"] =$("#order_seq_"+pDepe).val();
		params["order_seq1"] =$("#order_seq1_"+pDepe).val();
		params["remark"] =$("#remark_"+pDepe).val();
		
		if($("input:checkbox[id='use_yn_"+pDepe+"']").is(":checked")){
			params["use_yn"] = "Y";	
		}
		else{
			params["use_yn"] = "N";
		}
		
		// 화면에 출력 안되는 값 추가 및 최종 가공
		params["code_step"] = pDepe;
		if(params["order_seq"]==''){
			params["order_seq"]='0';
		}
		if(params["order_seq1"]==''){
			params["order_seq1"]='0';
		}
		return params;
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
<!-- 					<li style="background-image: url(./resources/assets/images/cover-img-3.jpg);"> -->
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
						<h3>공통코드</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<div class="panel panel-white">
								<div class="panel-heading border-bottom padding-10">
									<h3 class="panel-title">1차 분류 코드</h3>
								</div>
								<div id="divSearchInfo1" class="panel-body padding-10">
									<div class="form-inline">
										<div class="form-group">
											<label for="filter-folder-name">코드명</label> 
											<input id="code_name1" name="code_name" type="text" class="form-control" placeholder="">
										</div>
										<div class="form-group">
											<div class="pull-right match-height">
												<input type="button" name="btnSearch1" id="btnSearch1" value="검색" class="btn btn-primary btn-block">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-white">
								<div class="panel-heading border-bottom padding-10 clearfix">
									<span aria-hidden="true" class="fa fa-list"></span> 
									<strong id="spCount1">0</strong>건이 조회되었습니다
											
								</div>
								<div class="panel-body padding-10" style="height: 200px;">
									<table id="list1"></table> 
									<div id="pager"></div> 
								</div>
							</div>
							<div class="panel panel-white">
								<div class="panel-heading border-bottom padding-10 clearfix">
									<div class="pull-left match-height">
										<h4 class="panel-title v-center">
											<span aria-hidden="true" class="fa fa-info-circle"></span>상세정보
										</h4>
									</div>
									<div class="pull-right match-height" >
										<input type="button" name="btnDelete1" id="btnDelete1" value="삭제" class="btn btn-primary btn-block">
									</div>
									
									<div class="pull-right match-height" >
										<input type="button" name="btnSave1" id="btnSave1" value="저장" class="btn btn-primary btn-block">
									</div>
									<div class="pull-right match-height" >
										<input type="button" name="btnInin1" id="btnInin1" value="추가" class="btn btn-primary btn-block">
									</div>
									
								</div>
								<div id="divInfo1" class="panel-body padding-10">
									<div>
										<table class="table-grid responsive-table margin-bottom-8">
											<tr>
												<th><label for="details-code-name"> 코드</label></th>
												<td>
													<div class="form-inline">
														<div class="form-group">
															<input id="code_1" name="code" class="form-control" type="text">
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name"> 코드명 </label></th>
												<td>
													<input id="code_name_1" name="code_name" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드약어명</label></th>
												<td>
													<input id="code_sml_name_1" name="code_sml_name" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">영문코드명</label></th>
												<td>
													<input id="eng_name_1" name="eng_name" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">영문약어명</label></th>
												<td>
													<input id="eng_sml_name_1" name="eng_sml_name" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드연동1</label></th>
												<td>
													<input id="use_method1_1" name="use_method1" class="form-control" type="text">
													</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드연동2</label></th>
												<td>
													<input id="use_method2_1" name="use_method2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드연동3</label></th>
												<td>
													<input id="use_method3_1" name="use_method3" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">정렬</label></th>
												<td><input id="order_seq_1" name="order_seq" class="form-control num_only" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">정렬2</label></th>
												<td>
													<input id="order_seq1_1" name="order_seq1" class="form-control num_only" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">비고</label></th>
												<td>
													<input id="remark_1" name="remark" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">사용여부</label></th>
												<td>
													<label> <input id="use_yn_1" name="use_yn" type="checkbox" checked="checked"> Yes</label>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<div class="panel panel-white">
								<div class="panel-heading border-bottom padding-10">
									<h3 class="panel-title">2차 분류 코드</h3>
								</div>
								<div id="divSearchInfo1" class="panel-body padding-10">
									<div class="form-inline">
										<div class="form-group">
											<label for="filter-folder-name">코드명</label> 
											<input id="code_name2" type="text" class="form-control" placeholder="">
										</div>
										<div class="form-group">
											<div class="pull-right match-height">
												<input type="button" name="btnSearch2" id="btnSearch2" value="검색" class="btn btn-primary btn-block">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-white">
								<div class="panel-heading border-bottom padding-10 clearfix">
									<span aria-hidden="true" class="fa fa-list"></span> 
									<strong id="spCount2">0</strong>건이 조회되었습니다
								</div>
								<div class="panel-body padding-10" style="height: 200px;">
									<table id="list2"></table> 
									<div id="pager"></div> 
								</div>
							</div>
							<div class="panel panel-white">
								<div class="panel-heading border-bottom padding-10 clearfix">
									<div class="pull-left match-height">
										<h4 class="panel-title v-center">
											<span aria-hidden="true" class="fa fa-info-circle"></span>상세정보
										</h4>
									</div>
									<div class="pull-right match-height">
										<div class="pull-right match-height" >
											<input type="button" name="btnDelete2" id="btnDelete2" value="삭제" class="btn btn-primary btn-block">
										</div>
										
										<div class="pull-right match-height" >
											<input type="button" name="btnSave2" id="btnSave2" value="저장" class="btn btn-primary btn-block">
										</div>
										<div class="pull-right match-height" >
											<input type="button" name="btnInin2" id="btnInin2" value="추가" class="btn btn-primary btn-block">
										</div>
									</div>
								</div>
								<div id="divInfo2" class="panel-body padding-10">
									<div>
										<table class="table-grid responsive-table margin-bottom-8">
											<tr>
												<th><label for="details-code-name"> 코드</label></th>
												<td>
													<div class="form-inline">
														<div class="form-group">
															<input id="code_2" class="form-control" type="text">
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name"> 코드명 </label></th>
												<td>
													<input id="code_name_2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드약어명</label></th>
												<td>
													<input id="code_sml_name_2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">영문코드명</label></th>
												<td>
													<input id="eng_name_2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">영문약어명</label></th>
												<td>
													<input id="eng_sml_name_2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드연동1</label></th>
												<td>
													<input id="use_method1_2" class="form-control" type="text">
													</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드연동2</label></th>
												<td>
													<input id="use_method2_2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">코드연동3</label></th>
												<td>
													<input id="use_method3_2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">정렬</label></th>
												<td><input id="order_seq_2"
													class="form-control num_only" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">정렬2</label></th>
												<td>
													<input id="order_seq1_2" class="form-control num_only" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">비고</label></th>
												<td>
													<input id="remark_2" class="form-control" type="text">
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">사용여부</label></th>
												<td>
													<label> <input id="use_yn_2" type="checkbox" checked="checked"> Yes</label>
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
	</div>
	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- 푸터 들어가는 곳 -->
	<%-- 	<jsp:include page="../../inc/bottom.jsp" /> --%>



</body>
</html>

