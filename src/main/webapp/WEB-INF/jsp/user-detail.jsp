<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglibs.jsp" %>

<script type="text/javascript">

	$(document).ready(function() {
		$('.nav-tabs a:first').tab('show');
		
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		})	
	})

</script>

<br />

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
  	<c:forEach items="${user.blogs}" var="blog">
    	<li role="presentation" ><a href="#blog_${blog.id}" data-toggle="tab">${blog.name}</a></li>
    </c:forEach>
  </ul>

  <!-- Tab panes -->
	<div class="tab-content">
		<c:forEach items="${user.blogs}" var="blog">
			<div class="tab-pane" id="blog_${blog.id}">
	
				<h3>
					<c:out value="${blog.name}" /> 
					<a href='<spring:url value="/blog/remove/${blog.id}.htm" />'  class="btn btn-danger triggerRemove"> remove Blog </a>
				</h3>
	
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th>Date</th>
							<th>Title</th>
						</tr>
					</thead>
	
					<tbody>
						<c:forEach items="${blog.items}" var="item">
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
	
			</div>
		</c:forEach>
	</div>
	
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Delete Blog</h4>
      </div>
      <div class="modal-body">
        Do you really want to remove Blog?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a href=""  class="btn btn-danger removeBtn">remove Blog </a>
      </div>
    </div>
  </div>
</div>	