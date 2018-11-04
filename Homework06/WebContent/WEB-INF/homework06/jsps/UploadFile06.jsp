<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<h2>Upload File</h2>
		<form method='post' action='UploadFile06' enctype='multipart/form-data'>
			<p>File: <input type='file' name='name'/></p> 
			<input type='hidden' name='id' value='${param.id}'>
			<input type='submit' value='Upload'/>
			</form>
	</body>
</html>