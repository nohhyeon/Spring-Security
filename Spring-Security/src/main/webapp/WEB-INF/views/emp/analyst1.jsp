<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>분석원</title>
</head>
<body>
	<sec:authorize
		access="hasAnyRole('ADMIN', 'ANALYST', 'MANAGER', 'PRESIDENT')">
		<p>이 페이지는 분석원만 접근할 수 있습니다.</p>
	</sec:authorize>
	<sec:authorize
		access="!hasAnyRole('ADMIN', 'ANALYST', 'MANAGER', 'PRESIDENT')">
		<script type="text/javascript">
			alert('이 페이지에 접근할 권한이 없습니다.');
			location.href = '/';
		</script>
	</sec:authorize>
	<a href="/common/common1">공용 페이지</a>
</body>
</html>