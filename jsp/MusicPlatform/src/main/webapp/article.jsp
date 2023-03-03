<div class="articleArea">
	<%
	out.println("<div>" + "Current Session : " + session.getId() + "</div>");
	out.println("<div>" + "Current User : " + session.getAttribute("loginUser") + "</div>");
	%>
</div>
