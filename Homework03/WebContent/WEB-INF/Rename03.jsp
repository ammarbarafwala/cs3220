<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
<meta charset='ISO-8859-1'>

<title>Insert title here</title>
</head>
<body>
	<form method='post' action='Rename03'>
		Edit Name: <input type='text' name='name' value='${name}'/> 
	<input type='hidden' name='currentFolderId' value='${param.currentFolderId}'>
	<input type='hidden' name='parentFolderId' value='${param.parentFolderId}'>
	<input type='hidden' name='folderId' value='${param.folderId}'>
	<input type='submit' value='Save'/>
	</form>
</body>
</html>