<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" />

<!-- Latest compiled and minified JavaScript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.min.js"> </script>

<title><tiles:getAsString name="title" /></title>
</head>

<body>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %> 
 	<tilesx:useAttribute name="current" />
 	
	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand ${current == 'index' ? 'active' : ''}" href='<spring:url value="/" />'>Sudarshan's Blog</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="${current == 'index' ? 'active' : ''}"><a href='<spring:url value="/"/>'>Home</a></li>
						
						<security:authorize  access="hasRole('ROLE_ADMIN')">
							<li class="${current == 'users' ? 'active' : ''}"><a href="<spring:url value="/users.htm"/>">Users</a></li>
						</security:authorize>
						
						<security:authorize access="!isAuthenticated()">
							<li class="${current == 'login' ? 'active' : ''}"><a href="<spring:url value="/login.htm"/>">Login</a></li>
							<li class="${current == 'register' ? 'active' : ''}"><a href="<spring:url value="/register.htm"/>">Register</a></li>
						</security:authorize>
						
						<security:authorize access="isAuthenticated()">
							<li class="${current == 'accounts' ? 'active' : ''}"><a href="<spring:url value="/accounts.htm"/>">My Account</a></li>
						</security:authorize>
						
					</ul>
					<ul class="nav navbar-nav navbar-right">
					<security:authorize access="isAuthenticated()">
						<li><a href="<spring:url value="/logout"/>">LogOut</a></li>
					</security:authorize>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>


		<!-- /container -->
		<tiles:insertAttribute name="body" />
		<br /> 
		<tiles:insertAttribute name="footer" />

	</div>
</body>

</html>