<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>진짜 필요한 꿀~팁 - 위트래블</title>
<!-- header 들어가는 곳 -->
<jsp:include page="../../inc/intro.jsp" />
<!-- header 들어가는 곳 -->
<style type="text/css">
.buttons {
	float: right;
	margin-right: 20em;
	margin-top: 2em;
}

.honeytable {
	width: 100%
}


/*  tr>.tds {  */
/*  width: 30%;  */
/*  }  */
</style>
<link rel="stylesheet" href="./resources/assets/tour/css/fixed-table.css">
<script src="./resources/assets/tour/js/common.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// 추천
		$("#btnRecommendUp").click(function () {recommendUp();});
		$("#btnRecommendDown").click(function () {recommendDown();});
		// 댓글 좋아요 싫어요
		$(".btnLike").click(function () {
			var idx = $(this).attr('name'); 
			console.log(idx);
			Like(idx);
		});
		$(".btnHate").click(function () {
			var idx = $(this).attr('name'); 
			console.log(idx);
			Hate(idx);
			});
		
		// 댓글 수정 삭제
		$(".btnComDelete").click(function () {
			var idx = $(this).attr('id');
// 			console.log(idx);
			comDelete(idx);
		});

	
		
		
		var recommendUp = function() {
			var params = {};
    		params["type"] = "Board";
    		params["call_type"] = "getRecommendCountUp";
    		params["seq"] = ${bb.seq };
    		console.log(params);
    		Ajax(params, function(pResult) {
    	 		console.log(pResult);
    	 		$('#recommend_count').html(pResult);
    		},false);
		};
		
		var recommendDown = function() {
			var params = {};
    		params["type"] = "Board";
    		params["call_type"] = "getRecommendCountDown";
    		params["seq"] = ${bb.seq };
    		console.log(params);
    		Ajax(params, function(pResult) {
    	 		console.log(pResult);
    	 		$('#recommend_count').html(pResult);
    		},false);
		};
		
		var Like = function(idx) {
			var params = {};
    		params["type"] = "Board";
    		params["call_type"] = "getLike";
    		params["seq"] = idx;
    		console.log(params);
    		Ajax(params, function(pResult) {
    	 		console.log("like : " + pResult);
    	 		$('#like_count_'+idx).html(pResult);
    		},false);
		};
		
		var Hate = function(idx) {
			var params = {};
    		params["type"] = "Board";
    		params["call_type"] = "getHate";
    		params["seq"] = idx;
    		console.log(params);
    		Ajax(params, function(pResult) {
    	 		console.log("hate : " + pResult);
    	 		$('#hate_count_'+idx).html(pResult);
    		},false);
		};
		
		
		var comDelete = function(idx) {
			$.confirm({
			    title: '댓글 삭제',
			    content: '삭제하시겠습니까?',
			    buttons: {
			    	삭제: function () {
			    		var params = {};
			    		params["type"] = "Board";
			    		params["call_type"] = "comDelete";
			    		params["seq"] = idx;
			    		console.log(params);
			    		Ajax(params, function(pResult) {
			    	 		console.log(pResult);
			    	 		$('.idx').parent().parent().remove();;
			    		},false);
			    	},
			               취소: function () {
			         Toastr("info","삭제취소 하였습니다.");
			       }
			    }
			});
			
		};
		
	});

</script>
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
						<!-- 꿀팁공통메뉴 들어가는 곳 -->
							<jsp:include page="../../inc/honeySide.jsp" />
						<div class="col-three-forth animate-box">
							<h2>${bb.etc1 }</h2>
							<div class="row">
								<div class="col-md-12">
									<table class = "honeytable">
										<tr>
											<td>
												<table width="100%" cellpadding="0" cellspacing="0" border="0">
												</table>
<%-- 												<form action='<c:url value="/honeyWrite"/>'  method="post"> --%>
													<table class="table">
														  <tbody>
														  
														    <tr align="center">
														      <th scope="row">No</th>
														      <td class="tds" colspan="3">${bb.seq }</td>
														       <th scope="row">조회수</th>
														      <td class="tds" colspan="3">${bb.readcount }</td>
														      <th scope="row">추천수</th>
														      <td class="tds" colspan="3"><span id="recommend_count">${bb.recommend }</span></td>
														    </tr>
														    <tr  align="center">
														      <th scope="row" colspan="4">작성자</th>
														      <td class="tds" >${bb.nickName }</td>
														      <th scope="row"></th>
														      <td class="tds"></td>
														      <th scope="row">작성일</th>
														      <td class="tds" colspan="2"><fmt:formatDate value="${bb.regi_date }" pattern="yyyy-M-dd HH:mm" /></td>
														    </tr>
														    <tr>
														      <th scope="row" colspan="4">제목</th>
														      <td colspan="6" width="">${bb.subject }</td>
														    </tr>
														    <tr>
														      <th scope="row" colspan="3">첨부파일</th>
														      <td colspan="7">
														     <c:choose>
															     <c:when test="${!empty file }">
	<!-- 														     그러지마 -->
															      	<c:forEach var="item" items="${file }">
															      		<a href="#">${item.file_name }</a> <br>
	<%-- 														      		<a href="/download.ktds?fname=${item.file_name}">${item.file_name }</a> <br> --%>
															      	</c:forEach>
															      </c:when>
															       <c:otherwise>
															       -
															     </c:otherwise>
														     </c:choose>
														      </td>
	<!-- 													      <td colspan="8"> - </td> -->
														    </tr>
														    <tr>
														      <td colspan="10">${bb.content }</td>
														    </tr>
														  </tbody>
													</table>
													<c:if test='${regi_id == bb.regi_id}'>
														<input type="button" value="글수정" onclick="location.href='<c:url value="/honeyUpdate?seq=${bb.seq }"/>'" >
														<input type="button" value="글삭제" onclick="delete1()">
													</c:if>
													<input type="button" value="글목록" onclick="back()" >
													<div style="text-align: right;">
														<c:if test='${sessionScope.id != null}'>
															<button id="btnRecommendUp">추천하기</button>
															<button id="btnRecommendDown">추천취소</button>
														</c:if>
													</div>
												<script type="text/javascript">
												function delete1(){
													var result = confirm("게시물을 삭제하시겠습니까?");
													if(result == true) {
// 														location.href = '<c:url value="/tour/delete?seq=${bb.seq }&etc1=${bb.etc1}"/>';
														location.href = '<c:url value="/honey/delete?seq=${bb.seq }&etc1=${bb.etc1}"/>';
														} 
												}
												
												function back(){
													history.back();
												}
												</script>
												
<!-- 												</form> -->
											</td>
										</tr>
									</table>
									
									<!-- 	comment table -->
				
					<table class="table table-hover">
						<tr><td colspan="5" align="center">Comment</td></tr>
							<tr>
								<td width="10%" align="center" width="100">ID</td><td width="80%" colspan="4">Content</td>
							</tr>
							<tr>
							
								<c:choose>
									<c:when test='${sessionScope.id != null}'>
										<form action='<c:url value="honeyComment "/>' method="post" name="fr">
										<input type="hidden" name="board_seq" value="${bb.seq }">
											<td align="center" width="10%">
											<c:choose>
												<c:when test="${sessionScope.profile eq null }">
													<c:if test="${sessionScope.gender eq '02' }">
														<img src="resources/upload/profile/female.jpg" alt="Avatar" style="width:50px;">
													</c:if>
													<c:if test="${sessionScope.gender eq '01' }">
														<img src="resources/upload/profile/male.jpg" alt="Avatar" style="width:50px;">
													</c:if>
												</c:when>
												<c:otherwise>
													<img src="resources/upload/profile/${sessionScope.profile }" alt="Avatar" style="width:50px;">					
												</c:otherwise>
											</c:choose>	
											${sessionScope.nickName }</td>
											<td colspan="4" width="80%">
												<textarea name="content" cols="50" rows="3"></textarea>
												<input type="submit" name="resister" value="댓글쓰기">
											</td>
										</form>
									</c:when>
									<c:otherwise>
										<td colspan="5" align="center">
											<textarea name="content" cols="80" rows="3">로그인 이후 코멘트 작성을 하실 수 있습니다.</textarea>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>		
						<c:forEach var="bean" items="${commentList }">
							<tr>
								<td class="row" width="20%">
									<c:choose>
										<c:when test="${bean.profile eq null }">
											<c:if test="${bean.gender eq '02' }">
												<img src="resources/upload/profile/female.jpg" alt="Avatar" style="width:50px; border-radius: 50%;">
											</c:if>
											<c:if test="${bean.gender eq '01' }">
												<img src="resources/upload/profile/male.jpg" alt="Avatar" style="width:50px; border-radius: 50%;">
											</c:if>
										</c:when>
										<c:otherwise>
											<img src="resources/upload/profile/${bean.profile }" alt="Avatar" style="width:50px; border-radius: 50%;">					
										</c:otherwise>
									</c:choose>	
									${bean.nickname }
								</td>
								<td width="40%" class="commentContent">${bean.content }</td>
								<td width="10%"><button id="like_${bean.seq }" class="btnLike" name="${bean.seq }">좋아요</button> : <span id="like_count_${bean.seq }">${bean.likeCount }</span></td>
								<td width="10%"><button id="hate_${bean.seq }" class="btnHate" name="${bean.seq }">싫어요</button> : <span id="hate_count_${bean.seq }">${bean.hateCount }</span></td>
								<td width="10%"><fmt:formatDate value="${bean.regi_date }" pattern="yyyy-M-dd" /></td>
								<td width="10%">
<%-- 									<c:if test='${session_id == bean.regi_id }'> --%>
<%-- 										<button class="btnComDelete" id='${bean.seq }'>삭제하기</button> --%>
<%-- 									</c:if> --%>
								</td>
							</tr>
						</c:forEach>
							
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

