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

tr a {
	color: gray;
	font-style: bold;
}

tr th {
text-align: center;}
</style>
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
							<h2>날씨</h2><p>(총 ${pageBean.count } 개)</p>
							<div class="row">
								<div class="col-md-12">
									<table class="table table-hover" style="table-layout: fixed;">
									  <thead>
									    <tr class="table-info">
									      <th scope="col" width="10%">글번호</th>
									      <th scope="col">글머리</th>
									      <th scope="col" width="35%">제목</th>
									      <th scope="col">작성자</th>
									      <th scope="col">작성일</th>
									      <th scope="col">조회수</th>
									      <th scope="col" width="10%">추천수</th>
									    </tr>
									  </thead>
									  <tbody>
										  <c:forEach var="list" items="${list }">
										    <tr>
										      <th scope="row">${list.seq }</th>
										      <td align="center" >${list.etc1 }</td>
										      <td style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><a href="honeycontent?seq=${list.seq }">${list.subject } </a></td>
										      <td align="center">${list.regi_id }</td>
										      <!-- 날짜표시 jstl/fmt 태그라이브러리 추가 -->
										      <td align="center" ><fmt:formatDate value="${list.regi_date }" pattern="yyyy-M-dd" /></td>
										      <td align="center" >${list.readcount }</td>
										      <td align="center" >${list.recommend }</td>
										    </tr>
										   </c:forEach>
									  </tbody>
									</table>
									<%
										String id = (String)session.getAttribute("id");
										if(id!=null){
									%>
									  <input style="float: right;" type="button" class="btn btn-secondary" value="글쓰기" onclick="location.href='<c:url value="/honeyWrite"/>'">
								<%} %>
								</div>
										<!-- 페이징 처리-->
								<div class="col-md-12 text-center">
										<div style="text-align: center;">
										<ul class="pagination">
											<c:if test="${pageBean.startPage > pageBean.pageBlock }">
												<li><a href='<c:url value="/weather?pageNum=${pageBean.startPage-pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }"/>'>[이전]</a></li>
											</c:if>
											
											<c:forEach var="i" begin="${pageBean.startPage }" end="${pageBean.endPage }" step="1">
												<li><a href='<c:url value="/weather?pageNum=${i }&searchSelect=${searchSelect }&search=${search }"/>'>${i }</a></li>
											</c:forEach>
											
											<c:if test="${pageBean.endPage < pageBean.pageCount}">
												<li><a href='<c:url value="/weather?pageNum=${pageBean.startPage+pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }"/>'>[다음]</a></li>
											</c:if>
											</ul>
										</div>	
										</div>
										
										<div class="row  form-group">
										<!--검색기능-->
										<form action='<c:url value="/weather"/>' method="get" style="float: right;  width: 440px;">
										<div class="col-md-3" >
											<select name="searchSelect" style="width: 105px; height: 50px;" class="form-control2">
												<option value="subject" <c:if test='searchSelect == "subject"'>selected="selected"</c:if>>제목</option>
												<option value="nickName" <c:if test='searchSelect == "nickName"'>selected="selected"</c:if>>작성자</option>
												<option value="content" <c:if test='searchSelect == "content"'>selected="selected"</c:if>>내용</option>
											</select>
											</div>
											<div class="col-md-3" style="float:center;">
											<input type="text" name="search" class="form-control3" style="width: 215px;" <c:if test='search != null'>value=${search }</c:if>> 
											</div>
											<div class="col-md-3" style="float: right;">
											<input type="submit" class="btn" value="검색">
											</div>
										</form>
							</div>
						</div>
						
						
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-12 text-center"> -->
<!-- 								<ul class="pagination"> -->
<!-- 									<li class="disabled"><a href="#">&laquo;</a></li> -->
<!-- 									<li class="active"><a href="#">1</a></li> -->
<!-- 									<li><a href="#">2</a></li> -->
<!-- 									<li><a href="#">3</a></li> -->
<!-- 									<li><a href="#">4</a></li> -->
<!-- 									<li><a href="#">&raquo;</a></li> -->
<!-- 								</ul> -->
<%-- 								<input id="imageupload" class="btn btn-primary" style="float: right; margin-top: 2em;" type="button" value="사진등록" onclick="location.href='<c:url value="/imageUpload"/>'">  --%>
<!-- 							</div> -->
<!-- 						</div> -->
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

