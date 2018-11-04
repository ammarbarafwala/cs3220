<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Title</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<%
	String bc = request.getParameter("bc");
	String errorMessage = "";
	if (bc != null && bc.matches("[A-Fa-f0-9]{6}")) {
		Cookie c = new Cookie("bg-color", bc);
		response.addCookie(c);
		errorMessage = "";
	} else {
		if (bc != null) {
			errorMessage = "<p class='error'><i>Please enter correct 6 character hex value.</i></p>";
		}
		Cookie[] cs = request.getCookies();
		bc="";
		if (cs != null) {
			for (Cookie c : cs) {
				if (c.getName().equals("bg-color")) {
					bc = c.getValue();
					break;
				}
			}
		} else
			bc = "FFFFFF";
	}
%>
<style>
.jumbotron {
	text-align: center;
}

.error {
	color: red;
}

body {
	background-color: #<%=bc%>;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6">
				<div class="jumbotron">
					<h1>Color Changer</h1>
					<p>
						Background Color: #<%=bc%></p>
				</div>
				<form>
					<div class="form-group">
						<%=errorMessage%>
						<label>Enter a Color</label> <input type="text" name="bc"
							class="form-control" placeholder="Hex Value Only">
					</div>
					<button type="submit" class="btn btn-default">Change Color</button>
				</form>
			</div>
		</div>

	</div>
</body>
</html>
