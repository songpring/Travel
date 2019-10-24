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
		var id = '${board_id}';
		if (m == "정상적으로 삭제되었습니다." && id == "freeboard") {
			alert(m);
			location.href = '<c:url value="/freeboard"/>';
		} else if (m == "삭제 실패!") {
			alert(m);
			history.back();
		}
	</script>
</body>
</html>