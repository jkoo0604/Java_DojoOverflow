<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Question</title>
<link href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>Ask a question</h3>
		</div>
		<div class="row padding"></div>
		<div class="row">
			<form action="/questions" method="post">
  			<div class="form-group row">
			    <label for="question" class="col-sm-2 col-form-label">Question:</label>
			    <div class="col-sm-10">
			    	<p class="err">${questionerr}</p>
			    	<textarea name="question" rows="5" class="form-control"></textarea>
			    </div>
			</div>
			<div class="form-group row">
			    <label for="tags" class="col-sm-2 col-form-label">Tags:</label>
			    <div class="col-sm-10">
			    	<p class="err">${tagerr}</p>
			      	<input type="text" class="form-control" name="tags">
			    </div>
			</div>
			<div class="row form-group">
				<div class="col text-center">
				
					<input class="btn btn-dark btn-sm" type="submit" value="Submit">
				</div>
			</div>
			</form>
		</div>
		<div class="row padding"></div>
		<div class="row">
			<div class="col text-center">
				<a href="/questions" class="btn btn-outline-dark btn-sm">Back</a>
			</div>
		</div>
	</div>
</body>
</html>