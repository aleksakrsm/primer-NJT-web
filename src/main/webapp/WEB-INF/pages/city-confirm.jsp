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
	<p>This is page for city CONFIRM!</p>
	
	<c:if test="${not empty information}">
		<div>${information}</div>
	</c:if>
	<c:if test="${not empty exception}">
		<div>${exception}</div>
	</c:if>
	
	
	<form action="${pageContext.request.contextPath}/city/confirm" method="post">
		<div>City number:</div>
		<div><input type="text" name="number" readonly="readonly" value="${cityDto.number}"/></div>
		
		<div>City name:</div>
		<div><input type="text" name="name" readonly="readonly" value="${cityDto.name}"/></div>
		
		<button id=save>Confirm</button>
	</form>
</body>
</html>