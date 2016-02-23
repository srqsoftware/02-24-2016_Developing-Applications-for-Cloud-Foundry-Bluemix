<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>

<html>
<head>
<link rel="stylesheet" href="./css/sitestyle.css" type="text/css" />
<title>Activity Tracker</title>
</head>
<body>


	<div id="owrap">
	  <div id="iwrap">
			<div id="obanner">
			</div>
			<div id="content">
						<h1>Environment Variables:</h1>
						<br/><br/>
						<p>
		<%
			Map<String, String> environment = (Map<String,String>)pageContext.findAttribute("environment");
			for(String key : environment.keySet()) {
				String val = environment.get(key);
				out.print("Key: " + key + " val: " + val + "<br/>");
			}
		%>
						</p>
						<br/><br/>
<a href="./create.htm">Log a new Activity</a>
						<br/><br/>


			</div>
			<div id="foot">
				&nbsp; &nbsp; Activity Tracker
			</div>
	  </div>
	</div>
</body>

</html>
