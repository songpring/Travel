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
	
	// 버튼 이벤트 할당
	$("#btnSearch").click(function () { Search(); return false; });
	$("#btnAdd").click(function () { Add(); return false; });
	
	// 추가
	var Add = function() {
		location.replace('<c:url value="/adminBoardSettingInfo"/>');
	}
	
	// 검색
	var Search = function() {
		//초기화
		var numCols = table.columns().nodes().length;
	    for(var i=0; i<numCols; i++) { table.column(i).search(''); }
	
		var id = $("#txtSearchId").val();
		var name = $("#txtSearchName").val();
		var boardType = $("#selboardType option:selected").val();
		
		//console.log(boardType);
		
		if(id!=""){
			table.column("1").search(id);	
		}
		if(name!=""){
			table.column("2").search(name);	
		}
		if(boardType!=""){
			table.column("3").search(boardType);	
		}
		table.draw();
	}
	
	var table = $("#listTable").DataTable({
	    "serverSide": true
	    , "dom": '<"top"i>rt<"bottom"p>'
	    , "processing": true
	    //, "info":     false
	    //, "bFilter" : false
	    , "ajax": {
	        "url": "adminBoardSettingList",
	        "type": "POST",
	        "dataSrc": function(res) {
	            var data = res.data;
	            //console.log(res);
	            return data;
	        }
	    }
	    , "columns" : [
			{"data":"seq"} //	시퀀스	int ,"orderable": false
			, {"data":"board_id"} //	게시판 아이디	varchar
			, {"data":"board_name"} //	게시판 이름	varchar
			, {"data":"board_types"} //	게시판종류	varchar
			, {"data":"writing_authority" ,"searchable" : false} //	글쓰기 권한	varchar
			, {"data":"modification_authority" ,"searchable" : false} //	글수정 권한	varchar
			, {"data":"delete_authority" ,"searchable" : false} //	글삭제 권한	varchar
			, {"data":"read_authority" ,"searchable" : false} //	글읽기 권한	varchar
			, {"data":"page_size" ,"searchable" : false} //	페이지당 출력 갯수	int
			, {"data":"pages_size" ,"searchable" : false} //	페이징 갯수	int
			, {"data":"notification_use" ,"searchable" : false} //	공지 사용 여부	varchar
			, {"data":"file_use" ,"searchable" : false} //	첨부파일 사용여부	varchar
			, {"data":"re_write_use" ,"searchable" : false} //	답변사용여부	varchar
			, {"data":"comments_use" ,"searchable" : false} //	코멘트 사용 여부	varchar
// 			, {"data":"regi_id","visible": false ,"searchable" : false } //	등록자 ID	varchar
// 			, {"data":"regi_date","visible": false,"searchable" : false} //	등록일	datetime
// 			, {"data":"regi_ip_addr","visible": false,"searchable" : false} //	등록자 IP	varchar
// 			, {"data":"modifier_id","visible": false,"searchable" : false} //	수정자 ID	varchar
// 			, {"data":"modifier_date","visible": false,"searchable" : false} //	수정일	datetime
// 			, {"data":"modifier_ip_addr","visible": false,"searchable" : false} //	수정자 IP	varchar
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
        console.log("seq : "+data.seq);
        // 동적으로 form 만들어서 submit
        var $form = $('<form></form>');
        $form.attr('action', '<c:url value="/adminBoardSettingInfo"/>');
        $form.attr('method', 'post');
        $form.appendTo('body');
        var seq = $('<input type="hidden" value="'+data.seq+'" name="seq">');
        $form.append(seq);
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
						<h3>게시판 리스트</h3>
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
								<div class="form-group">
										<label for="filter-folder-name">게시판 ID</label> 
										<input id="txtSearchId" type="text" class="form-control" placeholder="">
									</div>
									<div class="form-group">
										<label for="filter-folder-name">게시판 이름</label> 
										<input id="txtSearchName" type="text" class="form-control" placeholder="">
									</div>
									<div class="form-group">
										<label for="filter-folder-name">게시판 타입</label> 
										${selboardType}
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
<!-- 							<div class="panel-heading border-bottom padding-10 clearfix"> -->
<!-- 								<span aria-hidden="true" class="fa fa-list"></span>  -->
<!-- 								<strong id="spCount1">0</strong>건이 조회되었습니다 -->
<!-- 							</div> -->
							<div class="panel-body padding-10">
								<table id="listTable" class="table table-bordered table-hover">
						  			<thead>
									<tr>
										<th>시퀀스</th>
					                    <th>게시판 아이디</th>
					                    <th>게시판 이름</th>
					                    <th>게시판종류</th>
					                    <th>글쓰기 권한</th>
					                    <th>글수정 권한</th>
					                    <th>글삭제 권한</th>
					                    <th>글읽기 권한</th>
					                    <th>페이지당 출력 갯수</th>
					                    <th>페이징 갯수</th>
					                    <th>공지 사용 여부</th>
					                    <th>첨부파일 사용여부</th>
					                    <th>답변사용여부</th>
					                    <th>코멘트 사용 여부</th>
<!-- 					                    <th>등록자 ID</th> -->
<!-- 					                    <th>등록일</th> -->
<!-- 					                    <th>등록자 IP</th> -->
<!-- 					                    <th>수정자 ID</th> -->
<!-- 					                    <th>수정일</th> -->
<!-- 					                    <th>수정자 IP</th> -->
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

