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
	This is page for city list.
	<div>
		<h1>All cities</h1>
		<table>
			<thead>
				<tr>
					<th>Code</th>
					<th>Number</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="city" items="${cities}">
				<tr>
					<td>${city.number}</td>
					<td>${city.name}</td>
					<td><a href="${pageContext.request.contextPath}/city/details?number=${city.number}"> details</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>