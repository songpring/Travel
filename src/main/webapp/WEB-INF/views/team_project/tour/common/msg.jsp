<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var m='${msg}';
var url = '${url}';
alert(m);

if(url!=""){
	location.href='<c:url value="'+url+'"/>';
	
}
else{
	history.back();	
}



// $(document).ready(function () {
// 	var mgs='${msg}';
// 	var url = '${url}';
// 	var type = '${type}';
// 	var mgsType = '${mgsType}';
// 	var urlType = '${urlType}';
// 	var resultType = '${resultType}';
	
// 	if(mgsType==""){
// 		mgsType = "info";
// 	}
// 	Toastr(mgsType,mgs);
	
// 	if(urlType=="post"){
// 		if(resultType>=0){
// 			// 뒤로 가기
// 			window.history.back();
// 		}
// 		else{
// 			// 0보다 크면 이동
// 			location.replace('<c:url value="'+url+'"/>');
// 		}
		
// 	}
// 	else if(urlType=="get"){
// 		if(resultType>=0){
// 			// 뒤로 가기
// 			window.history.back();
// 		}
// 		else{
// 			// 0보다 크면 이동
// 			location.replace('<c:url value="'+url+'"/>');
// 		}
		
// 	}
	
	
// })

</script>
</head>
<body>
</body>
</html>