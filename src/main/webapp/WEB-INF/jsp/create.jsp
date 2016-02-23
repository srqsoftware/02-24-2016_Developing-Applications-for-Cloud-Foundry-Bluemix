<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test='${activity.id=="0"}'>
	<c:set var="btn" value="Add Activity"/>
</c:if>
<c:if test='${activity.id!="0"}'>
	<c:set var="btn" value="Update Activity"/>
</c:if>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/sitestyle.css" type="text/css" />
<title>Activity</title>
</head>
<body>
	<div id="owrap">
	  <div id="iwrap">
			<div id="obanner">
			</div>
			<div id="content">
				<h1>Activity Details</h1>
				<form:form method="post" commandName="activity" action="create.htm">
				
					<p>Location: <form:input path="location" />
					<form:errors path="location" cssClass="error"/></p>
					
					<p>Distance: <form:input path="distance" />
					<form:errors path="distance" cssClass="error"/></p>

					<p>Duration: <form:input path="duration" />
					<form:errors path="duration" cssClass="error"/></p>
					
					<form:hidden path="id"/>
					<button type="submit" name="submitButton"><c:out value="${btn}"/></button>
				</form:form>
			</div>
			<div id="foot">
				Activity Tracker
			</div>
	  </div>
	</div>
</body>
</html>