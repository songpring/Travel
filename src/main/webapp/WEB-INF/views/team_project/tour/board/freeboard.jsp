<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>자유게시판 - 위트래블</title>
<!-- head 들어가는 곳 -->
<jsp:include page="../inc/intro.jsp" />
<!-- head 들어가는 곳 -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript">
   $(document).ready( function () {
       $('#myTable').DataTable();
   } );

   $(document).ready( function () {
       $('#noticeList').DataTable();
   } );
</script>
<style type="text/css">
.row-1 {
    margin-left: -15px;
    margin-right: -15px;
}
.recentlist {
   float: left;
   margin: 50px 0 50px 0px;
   width: 45%;
}
.hotlist{
   float:left;   
   margin: 50px 10px 50px 0;
   width: 45%;
}
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
   text-align: left;
   font-size: 12px;
}
</style>
<script src="./resources/assets/tour/js/common.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
      var params = {};
         params["type"] = "Board";
         params["call_type"] = "getHotList";
         params["board_id"] = "free";
//          console.log(params);
         Ajax(params, function(pResult) {
            var data = jQuery.parseJSON(pResult.data);
            console.log(data);
            //데이터 바인딩
            for (var index in data.Table) {
//                console.log(data.Table[index]);
                $('#hotList>tbody').append("<tr>")
                               .append('<td class="row">' +  data.Table[index]["seq"] + "</td>")
                               .append("<td>" + data.Table[index]["subject"] + "</td>")
                        	   .append("<td>" + data.Table[index]["nickName"] + "</td>")
                               .append("<td>" + data.Table[index]["etc3"] + "</td>")
                               .append("<td>" + data.Table[index]["readcount"] + "</td>")
                               .append("<td class='recommend'>" + data.Table[index]["recommend"] + "</td>")
                               .append("</tr>")
                               
            }
         },false);
   });
   
   $(document).ready(function(){
      var params = {};
         params["type"] = "Board";
         params["call_type"] = "getNoticeList";
         params["board_id"] = "free";
//          console.log(params);
         Ajax(params, function(pResult) {
            var data = jQuery.parseJSON(pResult.data);
            console.log(data);
            //데이터 바인딩
            for (var index in data.Table) {
//                console.log(data.Table[index]);
                $('#noticeList>tbody').append("<tr>")
                                   .append('<td class="row">' +  data.Table[index]["seq"] + "</td>")
                                   .append("<td>" + data.Table[index]["subject"] + "</td>" )
                                   .append("<td>" + data.Table[index]["nickName"] + "</td>")
                                   .append("<td>" + data.Table[index]["etc3"] + "</td>")
                                   .append("<td>" + data.Table[index]["readcount"] + "</td>")
                                   .append("<td class='recommend'>" + data.Table[index]["recommend"] + "</td>")
                                   .append("</tr>")
                               
            }
         },false);
   });
   
   </script>
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
            <li style="background-image: url(https://images.pexels.com/photos/2873686/pexels-photo-2873686.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
               <div class="overlay"></div>
               <div class="container-fluid">
                  <div class="row-1">
                     <div class="col-md-6 col-sm-12 col-md-offset-3 slider-text">
                        <div class="slider-text-inner text-center">
			   					<h2>자유롭게 대화하는</h2>
			   					<h1>자유게시판</h1>
                        </div>
                     </div>
                  </div>
               </div>
            </li>
           </ul>
        </div>
   </aside>

   <div id="colorlib-testimony" class="colorlib-light-grey">
      <div class="container">
         <div class="row">
<!--             <div class="col-md-6 col-md-offset-3 text-center colorlib-heading animate-box"> -->
<!--             </div> -->
            <div class="hotlist">
               <h3><b>인기글</b></h3>
               <table class="table table-hover" id="hotList">
                  <thead>
                     <tr>
                        <th width="10%">번호</th>
                           <th width="40%">제목</th>
                           <th width="10%">작성자</th>
                           <th width="20%">작성일</th>
                           <th width="10%">조회수</th>
                           <th width="10%">추천수</th>
                        </tr>
					</thead>
                  <tbody>
                     
                  </tbody>
               </table>
            </div>
            <div class="hotlist">
               <h3><b>공지</b></h3>
               <table class="table table-hover" id="noticeList">
                  <thead>
                     <tr>
                        <th width="10%">번호</th>
                           <th width="40%">제목</th>
                           <th width="10%">작성자</th>
                           <th width="20%">작성일</th>
                           <th width="10%">조회수</th>
                           <th width="10%">추천수</th>
                         </tr>
                        </thead>
                        
                  <tbody>
                  </tbody>
               </table>
            </div>
            
               <div class="freeboard">
               <table class="table table-hover" id="myTable" style="table-layout: fixed;">
                  <thead>
                     <tr class="table-info">
                        <th width="10%">번호</th>
                           <th width="40%">제목</th>
                           <th width="10%">작성자</th>
                           <th width="10%">작성일</th>
                           <th width="10%">조회수</th>
                           <th width="10%">추천수</th>
                         </tr>
                        </thead>
                  <tbody>
                     <c:forEach var="bean" items="${boardList }">
                        <tr>
                           <td class="row">${bean.seq }</td>
                           <td style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><a href='<c:url value="/freeboardContent?seq=${bean.seq} "/>'>${bean.subject }</a></td>
                           <td>${bean.nickName }</td>
                           <td>${bean.etc3 }</td>
                           <td>${bean.readcount }</td>
                           <td class="recommend">${bean.recommend }</td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
               
               <div class="row  form-group">
               <!--글쓰기 기능 -->
               <%
                  String id = (String)session.getAttribute("id");
                  if(id!=null){
               %>
                    <input style="float: right;" type="button"  class="btn" value="글쓰기" onclick="location.href='<c:url value="/freeboardWrite"/>'">
               <%} %>
               
               <!--검색기능-->
               <form action='<c:url value="/freeboard"/>' method="get" style="float: right; width: 440px;">
                 <div class="col-md-3">
                  <select name="searchSelect" style="width: 105px; height: 50px;" class="form-control2">
                     <option value="subject" <c:if test='${searchSelect == "subject"}'>selected="selected"</c:if>>제목</option>
                     <option value="nickName" <c:if test='${searchSelect == "nickName"}'>selected="selected"</c:if>>작성자</option>
                     <option value="content" <c:if test='${searchSelect == "content"}'>selected="selected"</c:if>>내용</option>
                  </select>
                  </div>
                  <div class="col-md-3" style="float:center;">
                  <input type="text" name="search" class="form-control3" style="width: 215px;" <c:if test='search != null'>value=${search }</c:if>> 
                  </div>
                  <div class="col-md-3" style="float: right;">
                  <input type="submit" class="btn" value="검색">
                  </div>
               </form>
               
               <!-- 페이징 처리-->
               <div class="col-md-12 text-center">
               <div style="text-align: center;">
               <ul class="pagination">
                  <c:if test="${pageBean.startPage > pageBean.pageBlock }">
                     <li><a href='<c:url value="/freeboard?pageNum=${pageBean.startPage-pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }"/>'>[이전]</a></li>
                  </c:if>
                  
                  <c:forEach var="i" begin="${pageBean.startPage }" end="${pageBean.endPage }" step="1">
                     <li ><a href='<c:url value="/freeboard?pageNum=${i }&searchSelect=${searchSelect }&search=${search }"/>'>${i }</a></li>
                  </c:forEach>
                  
                  <c:if test="${pageBean.endPage < pageBean.pageCount}">
                     <li><a href='<c:url value="/freeboard?pageNum=${pageBean.startPage+pageBean.pageBlock }&searchSelect=${searchSelect }&search=${search }"/>'>[다음]</a></li>
                  </c:if>
               </ul>
               </div>
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
<jsp:include page="../inc/bottom.jsp" />

</body>
</html>
