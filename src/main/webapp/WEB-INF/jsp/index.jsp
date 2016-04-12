<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglibs.jsp"%>

<div class="alert alert-info">
	<strong>Stay Tuned For News Feed</strong>, refreshes as we get any new updates.
</div>

<table class="table table-bordered table-hover table-striped indexPage">
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

<ul id="pagination" class="pagination pagination-sm"></ul>
           
<!-- 
<script type="text/javascript">
$(document).ready(function(){	
	$("#pagination").twbsPagination({
		totalPages : 50,
		visiblePages : 5,
		onPageClick : function(event, page) {
			setPage(page) //$(".indexPage").append('Page ' + page);
		}
	});
})


	function setPage(currentPage) {
		var param = {};
		param.currentPage = currentPage;
		param.limitPage = 5;
		
		$('.indexPage tbody').empty();

		for (var i = 0; i < 2; i++) {

			var tr = "<tr><td>" + "hjbj" + " </td>  <td>"
					+ "jnk" + " </td>  <td>" + "jnk"
					+ " </td>  <td>" + "jnk" + " </td>  <td>"
					+ "jnk" + " </td>  <td>"
					+ "jnk" + " </td>";

			$(".indexPage tbody").append(tr);

		}		

		

	}
</script>

 -->