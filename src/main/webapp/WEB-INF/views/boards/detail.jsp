<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />

	<c:if test="${principal.id == boardsContent.usersId}">
		<div class="d-flex">
			<a href="/boards/${boardsContent.id}/updateForm">
				<button class="btn btn-warning">수정하기</button>
			</a>

			<form action="${boardsContent.id}/delete" method="POST">
				<button class="btn btn-danger">삭제하기</button>
			</form>
		</div>
	</c:if>

	<br /> <br />
	<div>
		<h3>${boardsContent.title}</h3>
	</div>
	<hr />
	<div>${boardsContent.content}</div>


</div>

<%@ include file="../layout/footer.jsp"%>

