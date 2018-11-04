<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		
	</script>
	</head>
	<body>
		<h2>Create New Folder</h2>
		<form method='post' action='NewFolder06'>
			<p>New Folder: <input type='text' name='name'/></p>
			<input type='hidden' name='id' value='${param.id}'>
			<input type='submit' value='Create'/>
		</form>
	</body>
</html>