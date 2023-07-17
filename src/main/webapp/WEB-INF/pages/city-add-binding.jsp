<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>This is page for city add.</p>
	
	<c:if test="${not empty information}">
		<div>${information}</div>
	</c:if>
	<c:if test="${not empty exception}">
		<div>${exception}</div>
	</c:if>
	
	
	<form:form action="${pageContext.request.contextPath}/city/save-binding-validation" modelAttribute="cityDto" method="post">
		<div>City number:</div>
		<div><form:input type="text" path="number"/></div>
		<div><form:errors path="number" /></div>
		<div>City name:</div>
		<div><form:input type="text" path="name"/></div>
		<div><form:errors path="name" /></div>
		
		<button id=save>Save</button>
	</form:form>
</body>
</html>