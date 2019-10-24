<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="../../inc/intro.jsp" />
<c:choose>
	<c:when test="${mb.id!=null}">
		<title>회원 정보 </title>
	</c:when>
	<c:otherwise>
		<title>회원 추가</title>
	</c:otherwise>
</c:choose>

<script src='<c:url value="/resources/assets/tour/js/common.js"/>'></script>

<!-- 그리드용 -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/jquery-ui.min.css" /> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="./resources/assets/tour/jqGrid/css/ui.jqgrid.css" /> -->
<!-- <script src="./resources/assets/tour/jqGrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script> -->
<!-- <script src="./resources/assets/tour/jqGrid/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script> -->

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


<script type="text/javascript">
$(document).ready(function () {
// 	console.log("aaa");
// 	console.log('${mb.id}');
	
	$("#btnZipCode").click(function () { btnZipCode(); return false; });
	$("#btnSave").click(function () { Save(); return false; });
	$("#btnDelete").click(function () { Delete(); return false; });
	$("#btnList").click(function () { List(); return false; });
	$("#btnInin").click(function () { Init(); return false; });
	
	
	// 우편번호 찾기
	var btnZipCode = function() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수
				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}
				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
					// document.getElementById("sample6_extraAddress").value = extraAddr;
				} else {
					// document.getElementById("sample6_extraAddress").value = '';
				}
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('zip_code').value = data.zonecode;
				document.getElementById("address1").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("address2").focus();
			}
		}).open();
	};
	
	
	
	// 저장
	var Save = function(pDepe) {
		$.confirm({
		    title: '확인',
		    content: '저장하시겠습니까?',
		    buttons: {
		    	저장: function () {
		    		$("#phone").val($("#ddlPhone").val()+"-"+$("#phone2").val()+"-"+$("#phone3").val());
		    		$("#email").val($("#email1").val() + "@"+ $("#email2").val());
		    		$('#fr').submit();
		    	},
		               취소: function () {
		            Toastr("info","취소 하였습니다.");
		       }
		    }
		});
	};
	
	// 삭제
	var Delete = function(pDepe) {
		$.confirm({
		    title: '확인',
		    content: '삭제하시겠습니까?',
		    buttons: {
		    	삭제: function () {
 		    		$("#fr").attr("action", '<c:url value="/adminMemberInfoDelete"/>');
 		    		$('#fr').submit();
		    	},
		               취소: function () {
		            Toastr("info","취소 하였습니다.");
		       }
		    }
		});
	};
	
	// 리스트
	var List = function() {
		location.replace('<c:url value="/adminMemberList"/>');
	}
	
	// 추가(초기화)
	var Init = function() {
		var pTarget = "#divInfo";
		$(pTarget).find("input:text").val("");
        $(pTarget).find("textarea").val("");
        $(pTarget).find("select").val("");
        $(pTarget).find("input:checkbox").prop("checked", true);
        $(pTarget).find("input:radio").eq(0).prop("checked", true);
        $("#password").attr("readonly", false);
        $("#id").attr("readonly", false);
	}
	

	// 셀렉트 이벤트
	// 이메일
	$("select[id$=ddlEmail]").change(function () {
    	if($("select[id$=ddlEmail]").val()!="05"){
    		$("#email2").val($("select[id$=ddlEmail] option:selected").text());
    	}
    	else{
    		$("#email2").val("");
    	}
    });
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
						<c:choose>
							<c:when test="${mb.id!=null}">
								<h3>회원 정보</h3>
							</c:when>
							<c:otherwise>
								<h3>회원 추가</h3>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="panel panel-white">
							<form action='<c:url value="/adminMemberInfoUpdate"/>' method="POST" name="fr" id="fr" >
								<div id="divInfo" class="panel-body padding-10">
								<input type="hidden" name="phone" id="phone">
								<input type="hidden" name="email" id="email"> 
									<div>
										<table class="table-grid responsive-table margin-bottom-8">
											<tr>
												<th><label for="details-code-name">아이디</label></th>
												<td>
													<div class="form-inline">
														<c:choose>
															<c:when test="${mb.id ==null}">
																<label><input id="id" name="id" class="form-control" type="text"></label>
															</c:when>
															<c:otherwise>
																<label><input id="id" name="id" class="form-control" type="text" value="${mb.id}" readonly="readonly"></label>
															</c:otherwise>
														</c:choose>
														
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">비밀번호</label></th>
												<td>
													<div class="form-inline">
														<c:choose>
															<c:when test="${mb.id == null}">
																<label><input id="password" name="password" class="form-control" type="text"></label>
															</c:when>
															<c:otherwise>
																<label><input id="password" name="password" class="form-control" type="text" value="${mb.password}" readonly="readonly"></label>
															</c:otherwise>
														</c:choose>
														
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">이름</label></th>
												<td>
													<div class="form-inline">
														<label><input id="name" name="name" class="form-control" type="text" value="${mb.name}"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">영문이름</label></th>
												<td>
													<div class="form-inline">
														<label><input id="eng_name" name="eng_name" class="form-control" type="text" value="${mb.eng_name}"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">닉네임</label></th>
												<td>
													<div class="form-inline">
														<label><input id="nickname" name="nickname" class="form-control" type="text" value="${mb.nickname}"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">생년월일</label></th>
												<td>
													<div class="form-inline">
														<label><input id="birth" name="birth" class="form-control" type="text" value="${mb.birth}"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">전화번호</label></th>
												<td>
													<div class="form-inline">
														<div class="form-group">
															<label>${ddlPhone }</label>
														</div>
														<label><input id="phone2" name="phone2" class="form-control" type="text" value="${phone2}"></label>
														<label><input id="phone3" name="phone3" class="form-control onlyNumberBasic" type="text" value="${phone3}"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">이메일</label></th>
												<td>
													<div class="form-inline">
														<label><input id="email1" name="email1" class="form-control" type="text" value="${email1}"></label>
														<label>@</label>
														<label><input id="email2" name="email2" class="form-control" type="text" value="${email2}"></label>
														<div class="form-group">
															<label>${ddlEmail }</label>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">성별</label></th>
												<td>
													<div class="form-inline">
														<label>${gender }</label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">우편번호</label></th>
												<td>
													<div class="form-inline">
														<label><input id="zip_code" name="zip_code" class="form-control" type="text" value="${mb.zip_code}"></label>
														<div class="form-group">
															<input type="button" name="btnZipCode" id="btnZipCode" value="우편번호 찾기" class="btn btn-primary btn-block">
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">주소</label></th>
												<td>
													<div class="form-inline">
														<label><input id="address1" name="address1" class="form-control" type="text" value="${mb.address1}"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th><label for="details-code-name">상세주소</label></th>
												<td>
													<div class="form-inline">
														<label><input id="address2" name="address2" class="form-control" type="text" value="${mb.address2}"></label>
													</div>
												</td>
											</tr>
										</table>
									</div>
									<div class="panel-heading border-bottom padding-10 clearfix">
										<div class="pull-left match-height">
											<h4 class="panel-title v-center">
	<!-- 											<span aria-hidden="true" class="fa fa-info-circle"></span>상세정보 -->
											</h4>
										</div>
										
										<c:choose>
											<c:when test="${mb.id==null}">
												<div class="pull-right match-height" >
													<input type="button" name="btnSave" id="btnSave" value="저장" class="btn btn-primary btn-block">
												</div>
												<div class="pull-right match-height" >
													<input type="button" name="btnList" id="btnList" value="목록" class="btn btn-primary btn-block">
												</div>
											</c:when>
											<c:otherwise>
												
												<div class="pull-right match-height" >
													<input type="button" name="btnDelete" id="btnDelete" value="삭제" class="btn btn-primary btn-block">
												</div>
												
												<div class="pull-right match-height" >
													<input type="button" name="btnSave" id="btnSave" value="저장" class="btn btn-primary btn-block">
												</div>
												<div class="pull-right match-height" >
													<input type="button" name="btnInin" id="btnInin" value="추가" class="btn btn-primary btn-block">
												</div>
												<div class="pull-right match-height" >
													<input type="button" name="btnList" id="btnList" value="목록" class="btn btn-primary btn-block">
												</div>
											</c:otherwise>
										</c:choose>
										
										
									</div>
								</div>
							</form>
							
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

