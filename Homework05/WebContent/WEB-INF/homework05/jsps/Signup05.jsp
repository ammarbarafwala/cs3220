<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
</head>
<body>
	<h2>Sign Up Page</h2>
	${ error }
	<form action="Signup05" method="post">
		<p>Username: <input type="text" name="username"></p>
		<p>Password: <input type="password" name="password"></p>
		<input type="submit" value="Create Account">
	</form>
</body>
</html>