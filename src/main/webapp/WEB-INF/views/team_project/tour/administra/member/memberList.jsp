<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="../../inc/intro.jsp" />
<title>회원관리</title>
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
	
	// 버튼 이벤트 할당
	$("#btnSearch").click(function () { Search(); return false; });
	$("#btnAdd").click(function () { Add(); return false; });
	
	// 추가
	var Add = function() {
		location.replace('<c:url value="/adminMemberInfo"/>');
	}
	
	// 검색
	var Search = function() {
		//초기화
		var numCols = table.columns().nodes().length;
	    for(var i=0; i<numCols; i++) { table.column(i).search(''); }
	    
	    var data = $("#txtSearchData").val();
	    var Type = "";
	    if($("#ddlSearchType").val()==""){
	    	$("#txtSearchData").val("");
	    	data="";
	    }
	    else if($("#ddlSearchType").val()=="id"){
	    	Type = "0";
	    }
	    else if($("#ddlSearchType").val()=="name"){
	    	Type = "1";	
	    }
	    else if($("#ddlSearchType").val()=="nickname"){
	    	Type = "3";	
	    }
	
	    table.column(Type).search(data);	
		table.draw();
	}
	
	var table = $("#listTable").DataTable({
	    "serverSide": true
	    , "dom": '<"top"i>rt<"bottom"p>'
	    , "processing": true
	    //, "info":     false
	    //, "bFilter" : false
	    , "ajax": {
	        "url": "adminMemberList",
	        "type": "POST",
	        "dataSrc": function(res) {
	            var data = res.data;
	            console.log(res);
	            return data;
	        }
	    }
	    , "columns" : [
			{"data":"id"} // 아이디
			, {"data":"name"} // 이름
			, {"data":"eng_name" ,"searchable" : false} // 영문이름
			, {"data":"nickname"} // 닉네임
			, {"data":"birth" ,"searchable" : false} // 생년월일
			, {"data":"email" ,"searchable" : false} // 이메일
			, {"data":"phone" ,"searchable" : false} // 전화번호
			, {"data":"point" ,"searchable" : false} // 포인트
			, {"data":"mileage" ,"searchable" : false} // 적립금
			, {"data":"level" ,"searchable" : false} // 회원 등급
			, {"data":"regi_id" ,"searchable" : false} // 등록자 ID
			, {
				"data":"regi_date" 
				,"searchable" : false
				,"render": function(data, type, row){
					if(type=='display'){
						data=getFormatDate(data,"-");
	                }
	                return data;
	           	}
			  } // 등록일
	    ]
	    ,"language": {
	        "emptyTable": "데이터가 없어요.",
	        "lengthMenu": "페이지당 _MENU_ 개씩 보기",
	        "info": "<strong>_TOTAL_</strong>건이 조회되었습니다<br>",
	        "infoEmpty": "데이터 없음",
	        "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
	        "search": "에서 검색: ",
	        "zeroRecords": "일치하는 데이터가 없어요.",
	        "loadingRecords": "로딩중...",
	        "processing":     "잠시만 기다려 주세요...",
	        "paginate": {
	            "next": "다음",
	            "previous": "이전"
	        }
	    }

	});
	
	// 클릭 이벤트
	$('#listTable tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        //console.log(data);
        console.log("id : "+data.id);
        // 동적으로 form 만들어서 submit
        var $form = $('<form></form>');
        $form.attr('action', '<c:url value="/adminMemberInfo"/>');
        $form.attr('method', 'post');
        $form.appendTo('body');
        var id = $('<input type="hidden" value="'+data.id+'" name="id">');
        $form.append(id);
        $form.submit();
    } );
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
						<h3>전체 회원 리스트</h3>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="panel panel-white">
							<div class="panel-heading border-bottom padding-10">
								<h3 class="panel-title">검색</h3>
							</div>
							<div id="divSearchInfo1" class="panel-body padding-10">
								<div class="form-inline">
<!-- 									<div class="form-group"> -->
<!-- 											<label for="filter-folder-name">ID</label>  -->
<!-- 											<input id="txtSearchId" type="text" class="form-control" placeholder=""> -->
<!-- 										</div> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label for="filter-folder-name">이름</label>  -->
<!-- 										<input id="txtSearchName" type="text" class="form-control" placeholder=""> -->
<!-- 									</div> -->
									<div class="form-group">
										<label for="filter-folder-name">검색 조건</label> 
										${ddlSearchType}
										<input id="txtSearchData" type="text" class="form-control" placeholder="">
									</div>
									<div class="form-group">
										<div class="pull-right match-height">
											<input type="button" name="btnSearch" id="btnSearch" value="검색" class="btn btn-primary btn-block">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-white">
							<div class="panel-body padding-10">
								<table id="listTable" class="table table-bordered table-hover">
						  			<thead>
									<tr>
										<th>아이디</th>
										<th>이름</th>
										<th>영문이름</th>
										<th>닉네임</th>
										<th>생년월일</th>
										<th>이메일</th>
										<th>전화번호</th>
										<th>포인트</th>
										<th>적립금</th>
										<th>회원 등급</th>
										<th>등록자 ID</th>
										<th>등록일</th>
					                </tr>
								    </thead>
								</table>
							</div>
							<div class="panel-heading border-bottom padding-10 clearfix">
								<div class="pull-left match-height">
									<h4 class="panel-title v-center">
									</h4>
								</div>
								<div class="pull-right match-height" >
									<input type="button" name="btnAdd" id="btnAdd" value="추가" class="btn btn-primary btn-block">
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

