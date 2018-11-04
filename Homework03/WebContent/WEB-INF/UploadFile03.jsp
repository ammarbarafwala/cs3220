<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<form method='post' action='UploadFile03' enctype='multipart/form-data'>
			File: <input type='file' name='name'/> 
			<input type='hidden' name='currentFolderId' value='${param.currentFolderId}'>
			<input type='hidden' name='parentFolderId' value='${param.parentFolderId}'>
			<input type='submit' value='Upload'/>
			</form>
	</body>
</html>