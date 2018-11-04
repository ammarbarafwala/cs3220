<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<header>
			<h2>Online File Manager</h2>
			<a href="Logout05">Logout</a>
			
			<p>
				<c:set var="i" value="${ fn:length(homework05BackList)}"/>
				<a href="HomePage05">Home</a>
				<c:forEach begin="1" end="${ fn:length(homework05BackList)}" varStatus="status">
					>> <c:if test="${ !status.last }">
						<a href="HomePage05?id=${ homework05BackList[i-status.index].id }">${ homework05BackList[i-status.index].name }</a> 
					</c:if>
				</c:forEach>
				${ homework05BackList[0].name }
					<br><br>
				<c:set var="path1" value="?id=${ homework05BackList[0]==null?0:homework05BackList[0].id }"/>
				<a class="newfolder" href="NewFolder05${path1}">New Folder</a>
				 | <a class="upload" href="UploadFile05${path1}">Upload</a>
			</p>
		</header>
		
		<table>
		
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Size</th>
				<th>Operations</th>
			</tr>
			
			<c:forEach var="file" items="${homework05List}">
			
				<c:set var="path2" value="?id=${ file.id }"/>
				<tr>
					<c:choose>
						<c:when test="${file.folder}">
							<td><a href="HomePage05${path2}">${file.name}</a></td>
							<td><fmt:formatDate value="${file.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td></td>
						</c:when>
				 		<c:otherwise>
				 			<td><a href="DownloadFile05${path2}">${file.name}</a></td>
				 			<td><fmt:formatDate value="${file.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td><c:choose>
									<c:when test="${file.size<1024}">${file.size} B</c:when>
									<c:otherwise><fmt:parseNumber integerOnly="true" value="${file.size/1024}"/> KB</c:otherwise>
							</c:choose></td>
				 		</c:otherwise>
				 	</c:choose>
					<td>
						<a href="Delete05${path2}&parentId=${ file.parentId }&isFolder=${ file.folder }">Delete</a> | 
						<a href="Rename05${path2}&parentId=${ file.parentId }&name=${ file.name }">Rename</a>
					</td>
				</tr>
				
			</c:forEach>
			
		</table>
	</body>
</html>