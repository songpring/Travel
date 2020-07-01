<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>나의 작성 글 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->

<style type="text/css">
tr th {
text-align: center;
 
}
tr .tds {
text-align: center;
 
}
</style>
</head>
<body>
	<div class="colorlib-loader"></div>
	<div id="page"></div>
	<nav class="colorlib-nav" role="navigation">
		<!-- 탑메뉴 들어가는 곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 탑메뉴 들어가는 곳 -->
	</nav>
	<aside id="colorlib-hero">
		<div class="flexslider">
			<ul class="slides">
				<li style="background-image: url(https://images.pexels.com/photos/7112/woman-typing-writing-windows.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h2>위트래블</h2>
									<h1>나의 작성글</h1>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</aside>
	<article>
		<div id="colorlib-blog">
			<div class="container">
					<legend class="screen_out">나의 작성글</legend>
				<table class="table" style="table-layout: fixed;">
					<thead>
						<tr>
							<th class="header" width="50">글번호</th>
							<th class="header" width="150">게시판</th>
							<th class="header" width="250">제목</th>
							<th class="header" width="150">작성자(닉네임)</th>
							<th class="header" width="150">작성일</th>
							<th class="header" width="50">조회수</th>
						</tr>
					</thead>
					<tbody>
					
						
					<c:forEach var="bean" items="${boardList }">
						<tr>
							<td class="tds">${bean.seq}</td>
							
							<c:choose>
								<c:when test="${bean.board_id eq 'free'}">
									<td class="tds">자유</td>
								</c:when>
								<c:when test="${bean.board_id eq 'together'}">
									<td class="tds">동행</td>
								</c:when>
								<c:when test="${bean.board_id eq 'review'}">
									<td class="tds">후기</td>
								</c:when>
								<c:when test="${bean.board_id eq 'honey'}">
									<td class="tds">꿀팁</td>
								</c:when>
								<c:otherwise>
									<td class="tds">board_id없음</td>
								</c:otherwise>
							</c:choose>
							
							<td style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
								<c:choose>
									<c:when test="${bean.board_id eq 'free'}">
										<a href='<c:url value="/freeboardContent?seq=${bean.seq }"/>'>
									</c:when>
									<c:when test="${bean.board_id eq 'together'}">
										<a href='<c:url value="/togetherContent?seq=${bean.seq }"/>'>
									</c:when>
									<c:when test="${bean.board_id eq 'review'}">
										<a href='<c:url value="/reviewContent?seq=${bean.seq }"/>'>
									</c:when>
									<c:when test="${bean.board_id eq 'honey'}">
										<a href='<c:url value="/honeycontent?seq=${bean.seq }"/>'>
									</c:when>
								</c:choose>
								${bean.subject}</a></td>
							<td class="tds">${bean.nickName}</td>
							<td class="tds"><fmt:formatDate value="${bean.regi_date }" pattern="yyyy-M-dd" /></td>
							<td class="tds">${bean.readcount}</td>
						</tr>
					</c:forEach> 
					
					</tbody>
				</table>
				
				<div class="row  form-group">
				<!--검색기능-->
				<div>
					<form action='<c:url value="/writing"/>' method="get" style="float: right;  width: 440px;">
					<div class="col-md-3">
						<select name="searchSelect" style="width: 105px; height: 50px;" class="form-control2">
							<option value="subject" <c:if test='searchSelect == "subject"'>selected="selected"</c:if>>제목</option>
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
				<!-- 페이징 -->
				<div style="text-align: center;">
					<c:if test="${pageBean.startPage > pageBean.pageBlock }">
						<a href='<c:url value="/writing?pageNum=${pageBean.startPage-pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }"/>'>[이전]</a>
					</c:if>
					
					<c:forEach var="i" begin="${pageBean.startPage }" end="${pageBean.endPage }" step="1">
						<a href='<c:url value="/writing?pageNum=${i }&searchSelect=${searchSelect }&search=${search }"/>'>${i }</a>
					</c:forEach>
					
					<c:if test="${pageBean.endPage < pageBean.pageCount}">
						<a href='<c:url value="/writing?pageNum=${pageBean.startPage+pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }"/>'>[다음]</a>
					</c:if>
				</div>
			</div>
			
		</div>
	</article>

	<!-- 맨위로 가게하는 버튼 -->
	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp" />
</body>
</html>