<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="../inc/intro.jsp" />
<title>항공권 예매 내역</title>
<script src='<c:url value="/resources/assets/tour/js/common.js"/>'></script>

<!-- 그리드용 -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/ui.jqgrid.css" />
<script src="./resources/assets/tour/jqGrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="./resources/assets/tour/jqGrid/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>


<script type="text/javascript">
$(document).ready(function () {
	
	$(".btnCancel").on("click", function(e){
		var data = $(this).parent().parent();
		$.confirm({
		    title: '예매 취소',
		    content: '예매 취소하시겠습니까?',
		    buttons: {
		    	예: function () {
		    	    var params = {};
		    	    
		    	    $.each(data,function(){
		    	    	if($(this).find('input[name="seq"]').val()!=""){
		    	    		params["seq"] = $(this).find('input[name="seq"]').val()
		    	    	}
		    	    });
		    	    
		    	    var $form = $('<form></form>');
	    	 	        $form.attr('action', '<c:url value="/reservationCancel"/>');
	    	 	        $form.attr('method', 'GET');
	    	 	        $form.appendTo('body');
		    	 	        var seq = $('<input type="hidden" value="'+ params["seq"] +'" name="seq">');
		    	 	        $form.append(seq);
	    	 	        $form.submit();
		    	},
		               아니오: function () {
// 		            Toastr("info","취소 하였습니다.");
		       }
		    }
		});
	    
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
			   	<li style="background-image: url(https://images.pexels.com/photos/163792/model-planes-airplanes-miniatur-wunderland-hamburg-163792.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500);">
			   		<div class="overlay"></div>
			   		<div class="container-fluid">
			   			<div class="row-1">
				   			<div class="col-md-6 col-sm-12 col-md-offset-3 slider-text">
				   				<div class="slider-text-inner text-center">
				   					<h2>위트래블</h2>
				   					<h1>항공권 예매 내역</h1>
				   				</div>
				   			</div>
				   		</div>
			   		</div>
			   	</li>
			  	</ul>
		  	</div>
		</aside>

		<div id="colorlib-testimony" class="colorlib-light-grey">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 text-center colorlib-heading animate-box">
						<h3>항공권 예매 내역</h3>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="panel panel-white">
							<div id="divInfo" class="panel-body padding-10">
								<div>
									<table id="myReservationTable" >
										<thead>
											<tr class="table-info">
												<th style="width: 5%">예매번호</th>
												<th style="width: 12.5%">여행지</th>
												<th style="width: 15%">비행날짜</th>
												<th style="width: 15%">인원수</th>
												<th style="width: 10%">좌석등급</th>
									            <th style="width: 12.5%">항공사</th>
									            <th style="width: 10%">비행시간</th>
									            <th style="width: 10%">가격</th>
									            <th style="width: 10%">취소하기</th>
						       				</tr>
						     			</thead>
										<tbody>
											<c:forEach var="bean" items="${reservationList }">
												<tr>
													<td>
														<input name="seq" class="form-control" type="text" value="${bean.seq}" readonly="readonly">
													</td>
													<td>
														<input name="trip" class="form-control" type="text" value="${bean.trip}" readonly="readonly">
													</td>
													<td>
														<input name="date" class="form-control" type="text" value="${bean.date}" readonly="readonly">
													</td>
													<td>
														<input name="count" class="form-control" type="text" value="${bean.count}" readonly="readonly">
													</td>
													<td>
														<input name="comp" class="form-control" type="text" value="${bean.comp}" readonly="readonly">
													</td>
													<td>
														<input name="airline" class="form-control" type="text" value="${bean.airline}" readonly="readonly">
													</td>
													<td>
														<input name="time" class="form-control" type="text" value="${bean.time}" readonly="readonly">
													</td>
													<td>
														<input name="fare" class="form-control" type="text" value="${bean.fare}" readonly="readonly">
													</td>
													<td>
														<input type="button" value="취소하기" class="btn btn-primary btn-block btnCancel">
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
<!-- 									<div id="pager"></div>  -->
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
		<jsp:include page="../inc/bottom.jsp" />

	</body>
</html>
