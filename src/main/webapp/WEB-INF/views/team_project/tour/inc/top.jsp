<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
<div class="top-menu">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-2">
							<div id="colorlib-logo">
								<c:choose>
									<c:when test='${sessionScope.id  == "admin"}'>
										<a href="scdCode">WeTravel</a>
									</c:when>
									<c:otherwise>
										<a href="tourindex">WeTravel</a>
									</c:otherwise>
								</c:choose>
								
							
							</div>
						</div>
						<div class="col-xs-10 text-right menu-1">
							<ul>
<!-- 								<li class="active"><a href="tourindex">메인</a></li> -->
								<li><a href="tourindex">메인</a></li>
								<li class="has-dropdown">
									<a href="#">여행스토리</a>
									<ul class="dropdown">
										<li><a href="together">동행게시판</a></li>
										<li><a href="tourreview">여행후기</a></li>
										<li><a href="freeboard">자유게시판</a></li>
									</ul>
								</li>
								<li class="has-dropdown">
									<a href="honey">꿀팁</a>
<!-- 									<ul class="dropdown"> -->
<!-- 										<li><a href="honey">꿀팁</a></li> -->
<!-- 										<li><a href="countryInfo">나라별정보</a></li> -->
<!-- 									</ul> -->
									</li>
								<li><a href="gallery">위스타그램</a></li>
								<li><a href="tourcontact">고객센터</a></li>
								<%
									//세션값 가져와서 변수에 저장
									String id=(String)session.getAttribute("id");
									String nick=(String)session.getAttribute("nick");
									// 세션값이 없으면  loginForm.jsp 이동
									if(id==null){
										%>
								<li><a href="tourlogin">로그인</a></li>										
										<%
									} else {
									%>
									<li class="has-dropdown">
									<a href="mypage">${nickname } 님</a>
									<ul class="dropdown">
										<li><a href="mypage">마이페이지</a></li>
										<li><a href="message">메세지</a></li>
										<li><a href="logout">로그아웃</a></li>
									</ul>
									</li>
									<%}
									%>
							</ul>
						</div>
					</div>
				</div>
			</div>
</header>