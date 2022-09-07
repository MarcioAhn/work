<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Insert title here</title>
<style>
	div.w3-card-4 {
		width:70%;
		margin:20px  auto;
	}
</style>
</head>
<body>
<body>
	<div class="w3-container w3-card-4">
		<h1 class="w3-text-blue">LOGIN USER</h1>
		<h3 class="w3-text-blue">회원ID : ${USER.username}</h3>
		<h3 class="w3-text-blue">E-mail : ${USER.email}</h3>
		<h3 class="w3-text-blue">실명 : ${USER.realname}</h3>
		<h3 class="w3-text-blue">닉네임 : ${USER.nickname}</h3>
	</div>
</body>
</body>
</html>