<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var m = '${alert}';
		var id = '${etc1}';
		if (m == "정상적으로 삭제되었습니다." && id == "교통") {
			alert(m);
			location.href = '<c:url value="/traffic"/>';
		} else if (m == "정상적으로 삭제되었습니다." && id == "공항") {
			alert(m);
			location.href = '<c:url value="/airport"/>';
		} else if (m == "정상적으로 삭제되었습니다." && id == "문화") {
			alert(m);
			location.href = '<c:url value="/culture"/>';
		} else if (m == "정상적으로 삭제되었습니다." && id == "날씨") {
			alert(m);
			location.href = '<c:url value="/weather"/>';
		} else if (m == "정상적으로 삭제되었습니다." && id == "") {
			alert(m);
			location.href = '<c:url value="/honey"/>';
		} else if (m == "삭제 실패!") {
			alert(m);
			history.back();
		}
	</script>
</body>
</html>