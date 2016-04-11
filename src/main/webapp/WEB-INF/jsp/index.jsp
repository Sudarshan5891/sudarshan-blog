<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglibs.jsp"%>

<div class="alert alert-info">
	<strong>Stay Tuned For News Feed</strong>, refreshes as we get any new updates.
</div>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Date</th>
			<th>Title</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${items}" var="item">
			<tr>
				<td><c:out value="${item.publishedDate}" /></td>
				<td>
					<strong> 
						<a href='<c:out value="${item.link}" />'>
							<c:out value="${item.title}" />
						</a>
					</strong> <br />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>