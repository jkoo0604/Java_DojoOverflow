<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question Detail</title>
<link href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<h4>${question.question}</h4>
		</div>
		<div class="row padding"></div>
		<div class="row">
			Tags:
			<c:forEach items="${question.tags}" var="t">
				&nbsp;&nbsp;&nbsp;<a href="/tags/${t.id}" class="btn btn-outline-dark btn-sm btn-tag" role="button">${t.subject}</a>
			</c:forEach>
		</div>
		<div class="row padding"></div>
		<div class="row">
			<div class="col-sm-6">
				<div class="row">
					<table class="table table-striped">
						<thead class="thead-dark">
						    <tr>
						      <th scope="col">Answers</th>
						    </tr>
						  </thead>
						  <tbody>
							  	<c:forEach items="${question.answers}" var="a">
							    <tr>
								      <td><c:out value="${a.answer}"/></td>
							    </tr>
							    </c:forEach>
						  </tbody>
					</table>
				</div>
			</div>
			<div class="col-sm-6 padding-left">
				<div class="row">
					<div class="col">
						<p class="subtitle">Add your answer:</p>
					</div>
				</div>
				
				<div class="row">
					<form action="/questions/${question.id}/add" method="post">
					<%-- <input type="hidden" name="question" value="${question.id}"> --%>
						<div class="col-sm-12">
							
							<div class="row form-group">
								<div class="col-sm-4">
									<label class="col-form-label">Answer:</label>
								</div>
								<div class="col-sm-8">
									<p class="err">${answererr}</p>
					        		<textarea name="answer" class="form-control"></textarea>
								</div>
							</div>
							<div class="row form-group">
								<div class="col text-center">
									<input type="submit" value="Add Answer" class="btn btn-dark btn-sm"/>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="row padding"></div>
		<div class="row">
			<div class="col text-center">
				<a href="/questions" class="btn btn-dark btn-sm">Back</a>
			</div>
		</div>
	</div>
</body>
</html>