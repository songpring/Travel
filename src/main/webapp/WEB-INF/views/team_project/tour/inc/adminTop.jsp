<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<header>
	<div class="top-menu">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-2">
					<div id="colorlib-logo"><a href="tourindex">WeTravel</a></div>
				</div>
				<div class="col-xs-10 text-right menu-1">
					<ul>
						<li><a href="tourindex">메인</a></li>
						<li class="has-dropdown">
							<a href='<c:url value="/scdCode"/>'>코드설정</a>
							<ul class="dropdown">
								<li><a href='<c:url value="/scdCode"/>'>코드관리</a></li>
<!-- 								<li><a href="tourreview">기타</a></li> -->
<!-- 								<li><a href="freeboard">3</a></li> -->
							</ul>
						</li>
						<li class="has-dropdown">
							<a href='<c:url value="adminMemberList"/>'>회원관리</a>
							<ul class="dropdown">
								<li><a href='<c:url value="adminMemberList"/>'>회원목록</a></li>
								<li><a href='<c:url value="/adminMemberInfo"/>'>회원추가</a></li>
							</ul>
						</li>
						<li class="has-dropdown">
							<a href='<c:url value="/adminBoardSetting"/>'>게시판관리</a>
							<ul class="dropdown">
								<li><a href='<c:url value="/adminBoardSetting"/>'>게시판리스트</a></li>
								<li><a href='<c:url value="/adminBoardSettingInfo"/>'>게시판추가</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</header>