<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglibs.jsp" %>

<script type="text/javascript">
//var recall = true;
function loadDialog() { 
        var sessionAlive = ${pageContext.session.maxInactiveInterval};          
        var notifyBefore = 30; // Give client 15 seconds to choose.
        setTimeout(function() {       
            $(function() {
                $( "#dialog" ).dialog({
                    autoOpen: true,
                    maxWidth:400,
                    maxHeight: 200,
                    width: 400,
                    height: 200,
                    modal: true,
                    open: function(event, ui) {
                        setTimeout(function(){
                            $('#dialog').dialog('close'); 
                        }, notifyBefore * 1000);
                    },
                    buttons: {
                    "Yes": function() {  
                         $.get("/about", function(data,status){
                           // alert("Data: " + data + "\nStatus: " + status);
                          });                             
                        $('#dialog').dialog("close");
                        loadDialog();
                    },
                    "No": function() {  
                        $('#dialog').dialog("close");
                    }
                   },
                   close: function() {}
                });
              });
        }, (sessionAlive - notifyBefore) * 1000);
};

loadDialog(); 

</script>

<div id="dialog" title="Session Timeout Info" style="display:none">
  <p>
    Your session will be timeout within 30 seconds. Do you want to continue session?  
  </p>
</div>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>User Name</th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
					<a href='<spring:url value="/users/${user.id}.htm" />'> <c:out value="${user.name}" />  </a>
				</td>
				<td>
					<a href='<spring:url value="/users/remove/${user.id}.htm" />'  class="btn btn-danger triggerRemove"> Delete Account </a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Delete User</h4>
      </div>
      <div class="modal-body">
        Do you really want to remove User?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a href=""  class="btn btn-danger removeBtn">remove User </a>
      </div>
    </div>
  </div>
</div>	
	