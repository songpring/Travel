<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>나의 정보 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->
<script type="text/javascript">
   // 셀렉트 이벤트
     // 이메일
     $("select[id$=ddlEmail]").change(function () {
         if($("select[id$=ddlEmail]").val()!="05"){
            $("#email2").val($("select[id$=ddlEmail] option:selected").text());
         }
         else{
            $("#email2").val("");
    		$('#email2').focus();
         }
      });
   
 	});

      
   
</script>
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
				<li style="background-image: url(https://images.pexels.com/photos/1079034/pexels-photo-1079034.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260);">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h2>위트래블</h2>
									<h1>나의 정보</h1>
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
				<legend class="screen_out">나의 정보</legend>
<form>
    <div class="form-group row">
        <label for="id" class="col-sm-2 col-form-label">아이디</label>
        <div class="col-sm-10">
            <input type="text" class="form-control2" id="id" value="${mb.id}" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">이름</label>
        <div class="col-sm-10">
            <input type="text" class="form-control2" id="name" value="${mb.name}" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </div>
    </div>
     <div class="form-group row">
        <label for="eng_name" class="col-sm-2 col-form-label">영문 이름</label>
        <div class="col-sm-10">
            <input type="text" class="form-control2" id="eng_name" value="${mb.eng_name}" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </div>
    </div>
     <div class="form-group row">
        <label for="nickname" class="col-sm-2 col-form-label">닉네임</label> 
        <div class="col-sm-10">
            <input type="text" class="form-control2" id="nickname" value="${mb.nickname}" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </div>
    </div>
    <div class="form-group row">
        <label for="nickname" class="col-sm-2 col-form-label">성</label> 
        <div class="col-sm-10">
        <c:choose>
        <c:when test="${mb.gender eq '02'}">
            <input type="text" class="form-control2" id="nickname" value="여자" disabled="disabled"  style="border:none; box-shadow:none" readonly>
       </c:when>
       <c:otherwise>
            <input type="text" class="form-control2" id="nickname" value="남자" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </c:otherwise>
        </c:choose>
        </div>
    </div>
    
               
               <div class="row form-group">
                  <div class="col-md-3">
                     <label for="phone1">전화번호</label> 
                     <input type="text" id="phone1" name="phone1" class="form-control3" value="${phone1 }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
                  </div>
                  <div class="col-md-3 padding-bottom">
                     <label for="phone2">　</label> 
                     <input type="text" id="phone2" name="phone2" class="form-control3" value="${phone2 }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
                  </div>
                  <div class="col-md-3">
                     <label for="phone3">　</label>
                     <input type="text" id="phone3" name="phone3" class="form-control3" value="${phone3 }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
                  </div>
                     <input type="hidden" name="phone" id="phone">
               </div>
               
                  <div class="form-group row">
                  <div class="col-md-3 padding-bottom">
                     <label for="email1">이메일</label> 
                     <input type="text" id="email1" name="email1" class="form-control3" value="${email1 }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
                  </div>
                  <div class="col-md-3" style="width:75px;">
                     <label for="email">　</label> 
                     <input type="text" class="form-control4" value="@" style="padding: 1em;" disabled readonly>
                  </div>
                     <div class="col-md-3">
                     <label for="email2">　</label> 
                     <input type="text" id="email2" name="email2" class="form-control3" value="${email2 }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
                  </div>
               </div>

  <div class="form-group row">
        <label for="zip_code" class="col-sm-2 col-form-label">우편번호</label> 
        <div class="col-sm-10">
            <input type="text" class="form-control2" id="zip_code" value="${mb.zip_code }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </div>
    </div>
    
<div class="form-group row">
        <label for="address1" class="col-sm-2 col-form-label">주소</label>
        <div class="col-sm-10">
            <input type="text" class="form-control2" id="address1" value="${mb.address1 }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </div>
    </div>
            
<div class="form-group row">
        <label for="address2" class="col-sm-2 col-form-label">상세 주소</label>
        <div class="col-sm-10">
            <input type="text" class="form-control2" id="address2" value="${mb.address2 }" disabled="disabled"  style="border:none; box-shadow:none" readonly>
        </div>
    </div>
            </form>
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

