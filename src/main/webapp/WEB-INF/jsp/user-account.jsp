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
		
		$(".blogForm").validate({
			rules : {
				name : {
					required : true,
					minlength : 1
				},
				url : {
					required : true,
					url : true
				}				
			},
			highlight: function(element) {
				$(element).closest(".form-group").removeClass("has-success").addClass("has-error");
			},
			unhighlight: function(element) {
				$(element).closest(".form-group").removeClass("has-error").addClass("has-success");
			}		
		})		
	})

</script>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Add Blog
</button>

<!-- Modal -->
<form:form commandName="blog" class="form-horizontal blogForm">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Add New Blog</h4>
	      </div>
	      <div class="modal-body">
					<div class="form-group">

						<label for="name" class="col-sm-2 control-label">Name</label>

						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" />
						</div>
					</div>

					<div class="form-group">

						<label for="name" class="col-sm-2 control-label">Url</label>

						<div class="col-sm-10">
							<form:input path="url" cssClass="form-control" />
						</div>
					</div>
				</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <input type="submit" class="btn btn-primary" value="Save"/>
	      </div>
	    </div>
	  </div>
	</div>
</form:form>

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