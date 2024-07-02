<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>사장</title>
</head>
<body>
	<!-- ADMIN 또는 PRESIDENT'역할을 가지고 있는 사용자만 이 콘텐츠를 볼 수 있다. -->
	<sec:authorize access="hasAnyRole('ADMIN', 'PRESIDENT')">
		<!-- 조건을 만족하는 사용자에게만 이 문단이 표시된다. -->
		<p>이 페이지는 사장님만 접근할 수 있습니다.</p>
	</sec:authorize>
	<!-- ADMIN'또는 PRESIDENT 역할을 가지고 있지 않은 사용자에게는 아래 스크립트를 실행한다. -->
	<sec:authorize access="!hasAnyRole('ADMIN', 'PRESIDENT')">
		<script type="text/javascript">
			alert('이 페이지에 접근할 권한이 없습니다.');
			location.href = '/';
		</script>
	</sec:authorize>
	<!-- 모든 사용자에게 공용 페이지로의 링크를 표시한다.-->
	<a href="/common/common1">공용 페이지</a>
</body>
</html>