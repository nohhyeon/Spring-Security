<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>공영 페이지</title>
</head>
<body>
	<sec:authorize
		access="hasAnyRole('ADMIN', 'ANALYST', 'CLERK', 'SALESMAN', 'MANAGER', 'PRESIDENT')">
		<p>이 페이지는 분석가, 사무원, 판매원, 매니저, 사장님이 접근할 수 있습니다.</p>
	</sec:authorize>
	<sec:authorize
		access="!hasAnyRole('ADMIN', 'ANALYST', 'CLERK', 'SALESMAN', 'MANAGER', 'PRESIDENT')">
		<script type="text/javascript">
			alert('이 페이지에 접근할 권한이 없습니다.');
			location.href = '/';
		</script>
	</sec:authorize>
	<a href="/">메인 페이지</a>
</body>
</html>