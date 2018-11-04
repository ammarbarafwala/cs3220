<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
<meta charset='ISO-8859-1'>

<title>Insert title here</title>
</head>
<body>
	<h2>Edit Folder Name</h2>	
	<form method='post' action='Rename04'>
		<p>Edit Name: <input type='text' name='name' value='${name}'/></p> 
		<input type='hidden' name='currentFolderId' value='${param.currentFolderId}'>
		<input type='hidden' name='parentFolderId' value='${param.parentFolderId}'>
		<input type='submit' value='Save'/>
	</form>
</body>
</html>