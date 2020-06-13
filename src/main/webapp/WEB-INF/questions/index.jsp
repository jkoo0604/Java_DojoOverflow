<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dojo Overflow</title>
<link href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>Questions Dashboard</h3>
		</div>
		<div class="row padding"></div>
		<div class="row">
			<table class="table table-striped">
				<thead class="thead-dark">
				    <tr>
				      <th scope="col">Question</th>
				      <th scope="col">Tags</th>
				      <th scope="col"># of Answers</th>
				    </tr>
				  </thead>
				  <tbody>
					  	<c:forEach items="${questions}" var="q">
					    <tr>
					      <td><a href="/questions/${q.id}"><c:out value="${q.question}"/></a></td>
					      <td>
					      	<c:forEach items="${q.tags}" var="t"  varStatus="loop">
					      		<c:out value="${t.subject}"/>${loop.last ? '' : ', '}
					      	</c:forEach>
					      </td>
					      <td>${fn:length(q.answers)}</td>
					    </tr>
					    </c:forEach>
				  </tbody>
			</table>
		</div>
		<div class="row padding"></div>
		<div class="row">
			<div class="col">
				<a href="/questions/new" class="btn btn-dark btn-sm" role="button">New Question</a>
			</div>
		</div>
	</div>
</body>
</html>