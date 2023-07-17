<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
	<!-- save, saveDto, saveToConfirm -->
	<!--<form action="${pageContext.request.contextPath}/city/saveToConfirm" method="post">-->
	<!--<form action="${pageContext.request.contextPath}/city/save" method="post">-->
	<form action="${pageContext.request.contextPath}/city/saveDto" method="post">
		<div>City number:</div>
		<div><input type="text" name="number"/></div>
		
		<div>City name:</div>
		<div><input type="text" name="name"/></div>
		
		<button id=save>Save</button>
	</form>
</body>
</html>