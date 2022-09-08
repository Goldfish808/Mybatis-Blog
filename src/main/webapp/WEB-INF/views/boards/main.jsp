<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<!-- 여기에 forEach 필요하겠죠? -->
			<!--
			<c:forEach var="boards" items="${boardsList}">
				<tr>
					<td>${boards.id}</td>
					<td><a href="/boards/${boards.id}">${boards.title}</a></td>
					<td>${boards.username}</td>
				</tr>
			</c:forEach> 
			 -->
			<c:forEach var="boards" items="${boardsList}">
				<tr>
					<td>${boards.id}</td>
					<td><a href="/boards/${boards.id}">${boards.title}</a></td>
					<td>${boards.username}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

<!-- 	<div style="backgound-color: grey;"> -->
<%-- 		<h3>totalCount : ${paging.totalCount}</h3> --%>
<%-- 		<h3>totalPage : ${paging.totalPage}</h3> --%>
<%-- 		<h3>currentPage : ${paging.currentPage}</h3> --%>
<%-- 		<h3>islast: ${paging.last}</h3> --%>
<%-- 		<h3>isFirst: ${paging.first}</h3> --%>
<!-- 	</div> -->


<!-- 	<ul class="pagination"> -->
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${paging.first}"> --%>
<%-- 			<li class="page-item disabled"><a class="page-link" href="/?page=${param.page - 1}">Previous</a></li> --%>
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<%-- 				<li class="page-item"><a class="page-link" href="/?page=${param.page - 1}">Previous</a></li> --%>
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>

<%-- 		<c:forEach var="pag" begin="1" end="${paging.totalPage}" step="1"> --%>
<!-- 			<div> -->
<%-- 				--- <a href="/?page=${pag-1}">${pag}</a> --- --%>
<!-- 			</div> -->
<%-- 		</c:forEach> --%>
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${paging.last}"> --%>
<%-- 				<li class="page-item disabled"><a class="page-link" href="/?page=${param.page + 1}">Next</a></li> --%>
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<%-- 				<li class="page-item"><a class="page-link" href="/?page=${param.page + 1}">Next</a></li> --%>
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>

<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class='page-item ${paging.first ? "disabled" : ""}'><a class="page-link" href="/?page=${paging.currentPage - 1}">Previous</a></li>
			
			<c:forEach var="pag" begin="${paging.startPageNum}" end="${paging.lastPageNum}" step="1">
				<div>
					<li class='page-item ${paging.currentPage == pag-1 ? "active" : ""}'><a class="page-link" href="/?page=${pag-1}"> ${pag}</a></li>
				</div>
			</c:forEach>
			
			<li class='page-item ${paging.last ? "disabled" : ""}'><a class="page-link" href="/?page=${paging.currentPage + 1}">Next</a></li>
		</ul>
</div>

<!-- 	</ul> -->
</div>

<%@ include file="../layout/footer.jsp"%>

