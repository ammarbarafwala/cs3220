<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<h2>Create New Folder</h2>
		<form method='post' action='NewFolder04'>
			<p>New Folder: <input type='text' name='name'/></p>
			<input type='hidden' name='currentFolderId' value='${param.currentFolderId}'>
			<input type='hidden' name='parentFolderId' value='${param.parentFolderId}'>
			<input type='submit' value='Create'/>
		</form>
	</body>
</html>