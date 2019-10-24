<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ID / PW 찾기 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->
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
				<li style="background-image: url(images/cover-img-2.jpg);">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h2>위트래블</h2>
									<h1>ID / PW 찾기</h1>
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
			
				<div class="cont_g">
                <div class="info_detail" id="findIdHome">
                    <fieldset>
                        <legend class="screen_out">아이디찾기 인증 목록 폼</legend>
                        <ul class="list_certify">
                                                        <li>
                                <span class="choice_comm">
                                    <input type="radio" id="inpCertify1" name="searchType" class="inp_comm" value="sirenSms">
                                    <label for="inpCertify1" class="lab_comm">
                                        <span class="ico_find"></span>내 명의(이름)로 가입된 휴대폰 인증
                                    </label>
                                </span>
                                <div class="desc_involve">
                                    <span class="txt_certify">내 명의(이름)로 가입한 아이디와, 이름/휴대폰 번호가<br>일치한 아이디를 찾습니다.</span>
                                    <button type="button" class="btn_find btn_next" id="sirenSMSBtn">다음단계</button>
                                </div>
                            </li>
                                                        <li>
                                <span class="choice_comm">
                                    <input type="radio" id="inpCertify2" name="searchType" class="inp_comm" value="mobile">
                                    <label for="inpCertify2" class="lab_comm">
                                        <span class="ico_find"></span>내 정보에 등록된 휴대폰으로 찾기
                                    </label>
                                </span>
                                <form id="findIdMobileForm" action="/find/id.do?action=search&searchType=mobile" method="post">
                                <div class="desc_involve">
                                    <div class="box_detail">
                                        <div class="bg_find bg_data">
                                            <div class="bg_find inner_bg">
                                                <label for="searchMobileName" class="lab_g" id="searchMobileNameLabel">이름 또는 닉네임을 입력해 주세요.</label>
                                                <input type="text" id="searchMobileName" name="searchName" class="tf_g" maxlength="30" value="">
                                            </div>
                                        </div>
                                        <p class="desc_add emph_notice" style="display: none">이름을 정확히 입력해 주세요.</p>
                                    </div>
                                    <div class="box_detail">
                                        <div class="bg_find bg_nation">
                                            <div class="bg_find inner_bg">
                                                <strong class="screen_out">국가번호 선택 상자</strong>
                                                <span class="screen_out">선택내용 : </span>
                                                <a href="javascript:;" class="link_nationnum"></a>
                                                <div class="wrap_nationinfo">
                                                    <div class="box_nationinfo">
                                                        <span class="ico_find"></span>
                                                        <label for="inpNationInfo" class="txt_placeholder">국가 검색(국가번호, 국가명)</label>
                                                        <input id="inpNationInfo" type="text" class="inp_nationinfo" autocomplete="off">
                                                    </div>
                                                    <a href="#none" class="link_nationinfo"></a>
                                                    <ul class="list_nationinfo"></ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="bg_find bg_data">
                                            <div class="bg_find inner_bg">
                                                <label for="searchMobile" class="lab_g" id="searchMobileLabel">휴대폰 번호를 입력해 주세요.</label>
                                                <input type="text" id="searchMobile" name="searchMobile" class="tf_g" value="">
                                                <input type="hidden" id="searchMobileNational" name="searchMobileNational" value="">
                                                <input type="hidden" id="searchMobileNationalCode" name="searchMobileNationalCode" value="">
                                            </div>
                                        </div>
                                        <p class="desc_add emph_notice" style="display: none">휴대폰 또는 이메일 주소를 정확히 입력해 주세요.</p>
                                        <button type="submit" class="btn_find btn_next">다음단계</button>
                                    </div>
                                </div>
                                </form>
                            </li>
                                                        <li>
                                <span class="choice_comm">
                                    <input type="radio" id="inpCertify3" name="searchType" class="inp_comm" value="email">
                                    <label for="inpCertify3" class="lab_comm">
                                        <span class="ico_find"></span>내 정보에 등록된 이메일로 찾기
                                    </label>
                                </span>
                                <form id="findIdEmailForm" action="/find/id.do?action=search&searchType=email" method="post">
                                <div class="desc_involve">
                                    <div class="box_detail">
                                        <div class="bg_find bg_data">
                                            <div class="bg_find inner_bg">
                                                <label for="searchEmailName" class="lab_g" id="searchEmailNameLabel">이름 또는 닉네임을 입력해 주세요.</label>
                                                <input type="text" id="searchEmailName" name="searchName" class="tf_g" maxlength="30" value="">
                                            </div>
                                        </div>
                                        <p class="desc_add emph_notice" style="display: none">이름 또는 닉네임을 입력해 주세요.</p>
                                    </div>
                                    <div class="box_detail">
                                        <div class="bg_find bg_data">
                                            <div class="bg_find inner_bg">
                                                <label for="searchEmail" class="lab_g" id="searchEmailLabel">이메일 주소 전체를 입력해 주세요.</label>
                                                <input type="text" id="searchEmail" name="searchEmail" class="tf_g" value="">
                                            </div>
                                        </div>
                                        <p class="desc_add emph_notice" style="display: none;">이메일 주소를 정확하게 입력해 주세요</p>
                                        <button type="submit" class="btn_find btn_next">다음단계</button>
                                    </div>
                                </div>
                                </form>

				<div style="margin-top: 10px">
					<button type="button" class="btn btn-success" id="btnSignup">확인</button>
					<!-- <button type="button" class="btn btn-sm btn-primary" id="btnCancel">취소</button> -->
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