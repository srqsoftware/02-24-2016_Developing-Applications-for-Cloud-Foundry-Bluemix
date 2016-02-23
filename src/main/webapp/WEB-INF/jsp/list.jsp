<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crowleyworks.basicweb.model.Activity" %>

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
						<h1>All Activities</h1>
						<br/><br/>
<%
	List<Activity> activities = (List<Activity>)pageContext.findAttribute("activities");
	if ((activities != null) && (activities.size() > 0)) {
		out.print("<table><tr><td>Edit</td><td>Location</td><td>Distance</td><td>Duration</td></tr>");
		for(Activity curActivity : activities) {
			out.print("<tr><td><a href=\"edit.htm?id=" + curActivity.getId() + "\">X</a></td><td>" + curActivity.getLocation() + "</td><td>" + curActivity.getDistance() + "</td><td>" + curActivity.getDuration() + "</td></tr>");
		}
		out.print("</table>");
	} else {
		out.print("<p>There are no activities to display</p>");
	}
%>
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
