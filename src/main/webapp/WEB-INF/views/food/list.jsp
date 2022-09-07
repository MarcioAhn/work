<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="https://kit.fontawesome.com/464cf13c93.js"
	crossorigin="anonymous"></script>
<style>
div.food_body {
	width: 60%;
	margin: 10px auto;
	padding: 2rem;
}

div.complete {
	text-decoration: red line-through;
	color: #aaa;
}

div.icon {
	display: inline-block;
	margin-left: auto;
	font-size: 150%;
}

.page-box {
	width: 90%;
	margin: 15px auto;
	padding: 16px;
	border: 1px solid blue;
}

.page-body {
	list-style: none;
	display: flex;
	justify-content: center;
	align-items: center;
}

.page-item {
	margin: 2px;
}

.page-link {
	padding: 0.5rem 0.7rem;
	line-height: 1.25;
	color: #007bff;
	background-color: #fff;
	border: 1px solid #DEE2E6;
	text-decoration: none;
}

.page-link:hover {
	color: #0056B3;
	background-color: #E9ECF;
	border-color: #DEE2EF;
}

.page-link:focus {
	z-index: 3;
	outline: 0;
	box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.page-item.active .page-link {
	color: #fff;
	background-color: #007BFF;
	border-color: #007BFF;
}
</style>
<script>
document.addEventListener("DOMContentLoaded",()=>{
    const div_body = document.querySelector("div.food_body")
    
    div_body?.addEventListener("click",(e)=>{
        const target = e.target
        console.log(target)
        if(target.tagName === "I" 
        		&& target.classList?.contains("fa-pen-to-square")) {
        	
        	const seq = target?.dataset.seq
        	if(target?.classList.contains("complete")) {
        		alert("완료된 항목은 수정할 수 없음")
        		return false
        	}
        	document.location.href = "${rootPath}/food/update?t_seq=" + seq
        			
        } else if (target.tagName == "SPAN" 
        		&& target.classList?.contains("food_content")) {
        	const parentDiv = target.closest("DIV")
        	console.log(parentDiv)
        	const seq = parentDiv?.dataset.seq
        	document.location.href = "${rootPath}/food/comp?t_seq=" + seq 
        }
    })
})


</script>


<div class="food_body w3-card-4">
	<sec:authorize access="isAuthenticated()">
		<h1 class="w3-text-gray">
			[
			<sec:authentication property="principal.username" />
			] 님의 FOOD LIST
		</h1>
		<c:forEach items="${FOODS}" var="FOOD">
			<div data-seq="${FOOD.t_seq}" title="시작 : ${FOOD.t_sdate}"
				class="food_content w3-border w3-padding-16 w3-margin 
				<c:if test='${not empty FOOD.t_edate}'> complete </c:if>
				">
				< 섭취음식 : ${FOOD.t_content} > <span class="food_content w3-text">
					( 기록날짜 : ${FOOD.t_sdate } ) </span>


				<c:if test="${not empty FOOD.t_edate}">
					<span class="food_content">섭취완료</span>
					<span class="food_content">${FOOD.t_edate}</span>
					<span class="food_content">${FOOD.t_etime}</span>
				</c:if>

				<c:if test="${FOOD.t_complete}">
					<span class="food_content">섭취완료</span>
					<span class="food_content">${FOOD.t_edate}</span>
					<span class="food_content">${FOOD.t_etime}</span>
				</c:if>

				<div class="icon">
					<i data-seq="${FOOD.t_seq}" class="fa-solid fa-pen-to-square"></i>
					<a href="${rootPath}/food/${FOOD.t_seq}/delete"><i
						class="fa-solid fa-trash-can"></i></a>
				</div>
			</div>
		</c:forEach>
		<div class="page-box">
			<ul class="page-body">

				<c:if test="${PAGE.startPageNo >1 }">
					<li class="page-item"><a href="${rootPath}?pageno=1"
						class="page-link">1</a></li>

					<li class="page-item"><a
						href="${rootPath}?pageno=${PAGE.currentPageNo -1}"
						class="page-link">&lt;</a></li>

					<li><a class="page-link">&middot;&middot;&middot;&middot;</a>
					</li>


				</c:if>

				<c:forEach begin="${PAGE.startPageNo}" end="${PAGE.endPageNo}"
					var="page">
					<li
						class="page-item <c:if test="${PAGE.currentPageNo == page}">active</c:if>">
						<a href="${rootPath}?pageno=${page}" class="page-link">${page}</a>
					</li>
				</c:forEach>

				<c:if test="${PAGE.endPageNo != PAGE.finalPageNo }">

					<li class="page-item"><a
						href="${rootPath}?pageno=${PAGE.currentPageNo + 1}"
						class="page-link">&gt;</a></li>
					<li><a class="page-link">&middot;&middot;&middot;&middot;</a>
					</li>

					<li class="page-item"><a
						href="${rootPath}?pageno=${PAGE.finalPageNo}" class="page-link">${PAGE.finalPageNo}</a>
					</li>
				</c:if>
			</ul>
		</div>
		<form:form>
			<input name="t_content" value="${FOOD.t_content}"
				placeholder="FOOD Insert" class="w3-input w3-border" />
		</form:form>
	</sec:authorize>
</div>

