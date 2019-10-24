<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>회원 탈퇴 - 위트래블</title>
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
            <li style="background-image: url(https://image.shutterstock.com/image-photo/saying-goodbye-airport-silhouette-traveler-260nw-585257981.jpg);">
               <div class="overlay"></div>
               <div class="container-fluid">
                  <div class="row">
                     <div class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
                        <div class="slider-text-inner text-center">
                           <h2>위트래블</h2>
                           <h1>회원 탈퇴</h1>
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
				<legend class="screen_out">회원 탈퇴</legend>
            <form action='<c:url value="/delete"/>' method="post" name="fr">
               <div class="form-group">
                  <label for="id">아이디</label>
                  <input type="text" class="form-control2" id="id" name="id" value="${id}" readonly>
               </div>
               <div class="form-group">
                  <label for="password">비밀번호</label> 
                  <input type="password" class="form-control2" id="password" name="password" placeholder="비밀번호를 입력해주세요">
               </div>
            
               <div class="form-group">
                  <label for="reservationDetails">현재 예매내역</label> 
                  		<div class="panel panel-white">
							<div id="divInfo" class="panel-body padding-10">
								<div>
									<table id="myReservationTable" >
										<thead>
											<tr class="table-info">
												<th style="width: 11.11%">예매번호</th>
												<th style="width: 11.11%">여행지</th>
												<th style="width: 11.11%">비행날짜</th>
												<th style="width: 11.11%">인원수</th>
												<th style="width: 11.11%">좌석등급</th>
									            <th style="width: 11.11%">항공사</th>
									            <th style="width: 11.11%">비행시간</th>
									            <th style="width: 11.11%">가격</th>
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
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
               </div>
      
               <div style="margin-top: 10px">
                  <input type="submit" value="탈퇴하기" name="btnDelete" id="btnDelete" class="btn btn-danger">
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


