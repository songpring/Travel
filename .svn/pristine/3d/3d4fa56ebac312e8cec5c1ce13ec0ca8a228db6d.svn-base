<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>여행후기게시판 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->

<script type="text/javascript">
	console.log("여기 오냐?");
	$(document).ready(function() {
		$('input[name="view"]').change(function() {
			console.log("여기 오냐?111");

			var radioValue = $("input[name='view']:checked").val();

			console.log(radioValue);
			if (radioValue == "thumb") {
				jQuery('#1').show();
				jQuery('#2').hide();
			} else {
				jQuery('#2').show();
				jQuery('#1').hide();
			}
		});
		
		$('input[name="searchoption"]').change(function() {
			console.log("여기 오냐?222");
			var value = $('input[name="searchoption"]:checked').val();
			console.log(value);
			if (value == "country") {
				jQuery('#3').hide();
				jQuery('#4').show();
			} else{
				jQuery('#3').show();
				jQuery('#4').hide();
			}
		});
		
		
		
		
	});
</script>
<style type="text/css">
option {
color: gray;
}

</style>
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
						style="background-image: url(https://images.pexels.com/photos/1000443/pexels-photo-1000443.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
						<div class="overlay"></div>
						<div class="container-fluid">
							<div class="row">
								<div
									class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
									<div class="slider-text-inner text-center">
										<h2>리얼트래블러의</h2>
										<h1>여행후기</h1>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</aside>
		
		<div class="colorlib-wrap">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="wrap-division" id="1" <c:choose>
							<c:when test='${view == "thumb"}'>
								style="display: block;"
							</c:when>
							<c:otherwise>
								style="display: none;"
							</c:otherwise>
						</c:choose>>
							<c:forEach var="bean" items="${boardList }">
								<div class="col-md-6 col-sm-6 animate-box">
									<div class="hotel-entry">

										<c:if test="${bean.file_use eq 'N' }">
											<a href='<c:url value="/reviewcontent?seq=${bean.seq}&regi_id=${bean.regi_id } "/>'
												class="hotel-img"
												style="background-image: url(resources/upload/empty.PNG);">
										</c:if>
										<c:if test="${bean.file_use eq 'Y' }">
											<a href='<c:url value="/reviewcontent?seq=${bean.seq}&regi_id=${bean.regi_id} "/>'
												class="hotel-img"
												style="background-image: ;">${bean.thumb}
										</c:if>

										<p class="price">
											<span>추천 </span><small>${bean.recommend } 개</small>
										</p>
										</a>
										<div class="desc">
											<p class="star">
												<span><i class="icon-star-full"></i>${bean.country }</span>
											</p>
											<h4>
												<a href='<c:url value="/reviewcontent?seq=${bean.seq} "/>'>${bean.subject }</a>
											</h4>
											<span class="place">by ${bean.nickName }</span> <span
												class="place"><fmt:formatDate
													value="${bean.regi_date }" pattern="yyyy-M-dd" /></span>
										</div>
									</div>
								</div>
							</c:forEach>
							<!-- 페이징 처리-->
							<div class="col-md-12 text-center">
								<div style="text-align: center;">
									<ul class="pagination">
										<c:if test="${pageBean.startPage > pageBean.pageBlock }">
											<li><a
												href='<c:url value="/tourreview?pageNum=${pageBean.startPage-pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }&view=thumb"/>'>[이전]</a></li>
										</c:if>

										<c:forEach var="i" begin="${pageBean.startPage }"
											end="${pageBean.endPage }" step="1">
											<li><a
												href='<c:url value="/tourreview?pageNum=${i }&searchSelect=${searchSelect }&search=${search }&view=thumb"/>'>${i }</a></li>
										</c:forEach>

										<c:if test="${pageBean.endPage < pageBean.pageCount}">
											<li><a
												href='<c:url value="/tourreview?pageNum=${pageBean.startPage+pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }&view=thumb"/>'>[다음]</a></li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>


						<div class="wrap-division" id="2" <c:choose>
												<c:when test='${view == "list"}'>
													style="display: block;"
												</c:when>
												<c:otherwise>
													style="display: none;"
												</c:otherwise>
											</c:choose>>
							<div class="">
<!-- 							<div class="col-md-6 col-md-offset-3 text-center colorlib-heading animate-box"> -->
								<div class="hotel-entry">
									<table class="table table-hover" style="table-layout: fixed;">
										<thead>
											<tr class="table-info">
												<th width="5%">글번호</th>
												<th width="10%">지역</th>
												<th width="40%">제목</th>
												<th width="10%">작성자</th>
												<th width="15%">작성일</th>
												<th width="10%">조회수</th>
												<th width="10%">추천수</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="bean" items="${boardList }">
												<tr>
													<th scope="row"><input type="hidden" name="seq"
														value="${bean.seq }">${bean.seq }</th>
													<td align="center">${bean.country }</td>
													<td
														style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><a
														href="reviewcontent?seq=${bean.seq }">${bean.subject }
													</a></td>
													<td align="center">${bean.nickName }</td>
													<!-- 날짜표시 jstl/fmt 태그라이브러리 추가 -->
													<td align="center"><fmt:formatDate
															value="${bean.regi_date }" pattern="yyyy-M-dd" /></td>
													<td align="center">${bean.readcount }</td>
													<td align="center">${bean.recommend }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 페이징 처리-->
							<div class="col-md-12 text-center">
								<div style="text-align: center;">
									<ul class="pagination">
										<c:if test="${pageBean.startPage > pageBean.pageBlock }">
											<li><a
												href='<c:url value="/tourreview?pageNum=${pageBean.startPage-pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }&view=list"/>'>[이전]</a></li>
										</c:if>

										<c:forEach var="i" begin="${pageBean.startPage }"
											end="${pageBean.endPage }" step="1">
											<li><a
												href='<c:url value="/tourreview?pageNum=${i }&searchSelect=${searchSelect }&search=${search }&view=list"/>'>${i }</a></li>
										</c:forEach>

										<c:if test="${pageBean.endPage < pageBean.pageCount}">
											<li><a
												href='<c:url value="/tourreview?pageNum=${pageBean.startPage+pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }&view=list"/>'>[다음]</a></li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
						<input id="imageupload" class="btn btn-primary"
							style="float: right; margin-top: 2em;" type="button" value="글쓰기"
							onclick="location.href='<c:url value="/reviewWrite"/>'">


					</div>
					<!-- SIDEBAR-->
					<div class="col-md-3">
					
						<input type="radio" name="view" value="thumb" <c:if test='${view == "thumb"}'>checked="checked"</c:if>>섬네일
						<input type="radio" name="view" value="list" <c:if test='${view == "list"}'>checked="checked"</c:if>>리스트
						<div class="sidebar-wrap">
							<div class="side search-wrap animate-box">
								<form action='<c:url value="/tourreview"/>' method="GET" class="colorlib-form">
									<h3 class="sidebar-heading">후기검색</h3>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label for="date">기간(부터):</label>
												<div class="form-field">
													<i class="icon icon-calendar2"></i> <input type="text"
														id="date" class="form-control date" placeholder="선택"
														autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label for="date">기간(까지):</label>
												<div class="form-field">
													<i class="icon icon-calendar2"></i> <input type="text"
														id="date" class="form-control date" placeholder="선택"
														autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label for="guests"> 
													제목 <input type="radio" name="searchoption" value="subject" checked="checked">
													나라별 <input type="radio"  name="searchoption" value="country" id="country">
													작성자 <input type="radio" name="searchoption" value="nickname"><br> 
													<input id="3" type="text" name="searchbox-review" size="30" style="color: black;">
													<select name="country" id="4" style="display: none; color: gray;">
															<option value="KOR">한국</option>
															<option value="CHN">중국</option>
															<option value="JAP">일본</option>
															<option value="ESA">동남아시아</option>
															<option value="WSA">서남아시아</option>
															<option value="EUR">유럽</option>
															<option value="AMR">아메리카</option>
															<option value="OCN">오세아니아</option>
															<option value="AFR">중동/아프리카</option>
													</select>
												</label>
											</div>
										</div>
										<div class="col-md-12" id="3">
											<input type="submit" id="submit" value="검색"
												class="btn btn-primary btn-block">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

<!-- 			<div id="colorlib-subscribe" -->
<!-- 				style="background-image: url(.resources/assets/tour/images/img_bg_2.jpg);" -->
<!-- 				data-stellar-background-ratio="0.5"> -->
<!-- 				<div class="overlay"></div> -->
<!-- 				<div class="container"> -->
<!-- 					<div class="row"> -->
<!-- 						<div -->
<!-- 							class="col-md-6 col-md-offset-3 text-center colorlib-heading animate-box"> -->
<!-- 							<h2>뉴스레터</h2> -->
<!-- 							<p>최신여행정보를 받아보세요.</p> -->
<%-- 							<form action='<c:url value="/mailSend"/>' method="post" --%>
<!-- 								class="form-inline qbstp-header-subscribe"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-md-12 col-md-offset-0"> -->
<!-- 										<div class="form-group"> -->
<!-- 											<input type="text" class="form-control" name="tomail" -->
<!-- 												id="email" placeholder="이메일주소 입력란"> <input -->
<!-- 												type="hidden" value="tourreview" name="url"> -->
<!-- 											<button type="submit" class="btn btn-primary">구독하기</button> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</form> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->

			<!-- 이메일구독 들어가는 곳 -->
		<jsp:include page="../inc/email.jsp" />
		
		<div class="gototop js-top">
			<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
		</div>

		<!-- 푸터 들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp" />
</body>
</html>

