<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://kit.fontawesome.com/464cf13c93.js"
	crossorigin="anonymous"></script>
<script src="${rootPath}/static/js/roulette.js?ver=2022-08-18-003"></script>
<style>
* {
	box-sizing: border;
	margin: 0;
	padding: 0;
}

html {
	width: 100vw;
	height: 100vh;
}

body {
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
}

header {
	padding: 2rem;
	color: white;
	background-color: #13c4d1;
	display: flex;
	justify-content: center;
	align-items: center;
}

nav {
	background-color: #23dbc6;
	color: white;
}

nav ul {
	list-style: none;
	display: flex;
}

nav li {
	padding: 16px 12px;
}

nav a {
	text-decoration: none;
	color: inherit;
	magin: 5px 0;
	padding: 0 12px;
	border-bottom: 3px solid transparent;
	transition: 1s;
}

nav a:hover {
	border-bottom: 3px solid #ddd
}

nav li:nth-of-type(4) {
	margin-left: auto;
}

nav li:nth-of-type(1) {
	margin-left: 20px;
}

nav li:last-of-type {
	margin-right: 30px;
}

section.main {
	flex: 1;
}

article.welcome {
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.list_box {
	display: flex;
	overflow: hidden;
	height: 200px;
}

.menu_rec {
	margin: 100px auto;
	width: 650px;
}

div.list_today {
	/* display: flex; */
	flex-direction: column;
}

div.list_row {
	display: flex;
	flex-direction: column;
	width: auto;
	margin: 20px auto 0 auto;
	
}
.inner_container {
	display: flex;
	flex-direction: column;
}

.list_row img {
	margin: 10px 0;
}

.text-box {
	margin: auto 20px auto 30px;
}



.img-list-home {
	width: 150px;
}

button {
	color: white;
	background-color: #23dbc6;
	border: none;
}
.select {
	color: white;
	background-color: #23dbc6;
	width: max-content;		
}
a {
	text-decoration: none;
}
.rotate {
	margin: 10px;
	border: none;
	}

.fa-play  {
	font-size : 300%;
	width: 70px;
	cursor: pointer;
}
.fa-stop {
	font-size : 300%;
	width: 70px;
	cursor: pointer;
}


div.list_row img {
	opacity: 1;
	transition: 1s ease-in-out;
}

div.ticker {
	overflow: hidden;
}

@keyframes ticker-slide {
        0% {
          transform: translateY(0);
        }
        100% {
          transform: translateY(-4400px);
        }
      }
/* 다수의 이미지를 담고 있는 box */
div.list_row {
	animation: ticker-slide 8s linear infinite;
	overflow: hidden;
}
div.ticker:hover div.list_row {
        animation-play-state: paused;
        cursor: pointer;
      }
div.stop-ani {
	animation-play-state: paused;
}

div.img-box img {
	opacity: 1;
	transition: opacity 0s ease-in-out;
}

div.img-box {
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-left: 15px;
}



div.img-box img {
	width: 180px;
	height: 180px;
}
</style>
</head>
<body>
	<header>
		<h1><i class="fa-solid fa-bowl-food"> 메뉴를 추천해드립니다</i></h1>
	</header>
	<nav>
		<ul>
			<li><a href="${rootPath}/">HOME</a></li>
			<li><a href="${rootPath}/food">음식 기록 일지</a></li>
			<li><a href="${rootPath}/about">About</a></li>

			<sec:authorize access="isAnonymous()">
				<li><a href="${rootPath}/user/login">로그인</a></li>
				<li><a href="${rootPath}/user/join">회원가입</a></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<li><form:form class="logout" action="${rootPath}/logout">
						<button>로그아웃</button>
					</form:form></li>
				<li><a href="${rootPath}/user/mypage">myPage</a></li>
			</sec:authorize>
		</ul>
	</nav>
	<section class="main">
		<c:choose>
			<c:when test="${LAYOUT == 'JOIN' }">
				<%@ include file="/WEB-INF/views/user/join.jsp"%>
			</c:when>
			<c:when test="${LAYOUT == 'LOGIN' }">
				<%@ include file="/WEB-INF/views/user/login.jsp"%>
			</c:when>
			<c:when test="${LAYOUT == 'FOOD_LIST' }">
				<%@ include file="/WEB-INF/views/food/list.jsp"%>
			</c:when>
			<c:when test="${LAYOUT == 'MYPAGE' }">
				<%@ include file="/WEB-INF/views/user/mypage.jsp"%>
			</c:when>

		</c:choose>
	
		<c:if test="${empty userID}">

			<article class="welcome">
				<h1>메뉴추천 애플리케이션 2022</h1>
				<p>MR을 이용하시려면 회원가입, 로그인을 해 주세요</p>
			</article>

		</c:if>
		<c:if test="${not empty userID}">
			<div class="menu_rec">
				<h2>메뉴를 추천합니다</h2>
				<div class="list_box">
					<div class="list_today">
						<div class="ticker">
							<div class="list_row"<%-- data-seq="${RAND.RCP_SEQ}" data-nm="${RAND.RCP_NM}" --%>>
								<c:forEach items="${RECIPES}" var="RAND">
									<div class="img-box">
										<img class="img-list-home" src="${RAND.ATT_FILE_NO_MK}" />
										<div class="inner_container">
										<div>${RAND.RCP_NM}</div>
										<div class="select"><a href="${rootPath}/food/${RAND.RCP_SEQ}/addEvent">선택</a></div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="rotate btn_stop">
					<button>
						<i class="fa-solid fa-stop btn_click"></i></button>
					</div>
					<div class="rotate btn_start">
					<button>
						<i class="fa-solid fa-play"></i></button>
					</div>
				</div>
			</div>
		</c:if>
	</section>
	<form:form class="logout" action="${rootPath}/logout" />

</body>
</html>