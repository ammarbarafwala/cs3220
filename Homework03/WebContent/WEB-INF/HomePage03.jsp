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
			
			<p>
				<fmt:parseNumber type="number" var="currentFolderId" value="0"/>
				<fmt:parseNumber type="number" var="parentFolderId" value="0"/>
				<c:if test="${param.currentFolderId!=null && param.currentFolderId!=0}">
				
					<fmt:parseNumber type="number" var="currentFolderId" value="${param.currentFolderId}"/>
					<fmt:parseNumber type="number" var="parentFolderId" value="${param.parentFolderId}"/>
					<c:set value="${homework03Map[parentFolderId][currentFolderId]}" var="currentFolder"/>
					
					<c:if test="${currentFolder.parent!=null}">
						<c:set var="back" value="?currentFolderId=${param.parentFolderId}
											&parentFolderId=${currentFolder.parent.parent==null?0:currentFolder.parent.parent.id}"/>
					</c:if>
					<a href="HomePage03${back}">..</a> \ <c:out value=" ${currentFolder.name}"/><br><br>
					
				</c:if>
				
				<c:set var="path1" value="?currentFolderId=${currentFolderId}&parentFolderId=${parentFolderId}"/>
				<a class="newfolder" href="NewFolder03${path1}">New Folder</a>
				 | <a class="upload" href="UploadFile03${path1}">Upload</a>
			</p>
		</header>
		
		<table>
		
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Size</th>
				<th>Operations</th>
			</tr>
			
			<c:forEach var="list" items="${homework03Map[currentFolderId]}">
			
				<c:set var="path2" value="?currentFolderId=${list.value.id}&parentFolderId=${currentFolderId}"/>
				<tr>
					<c:choose>
						<c:when test="${list.value.folder}">
							<td><a href="HomePage03${path2}">${list.value.name}</a></td>
							<td><fmt:formatDate value="${list.value.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td></td>
						</c:when>
				 		<c:otherwise>
				 			<td><a href="DownloadFile03${path2}">${list.value.name}</a></td>
				 			<td><fmt:formatDate value="${list.value.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td><c:choose>
									<c:when test="${list.value.size<1024}">${list.value.size} B</c:when>
									<c:otherwise><fmt:parseNumber integerOnly="true" value="${list.value.size/1024}"/> KB</c:otherwise>
							</c:choose></td>
				 		</c:otherwise>
				 	</c:choose>
					<td>
						<a href="Delete03${path1}&folderId=${list.value.id}">Delete</a> | 
						<a href="Rename03${path1}&folderId=${list.value.id}">Rename</a>
					</td>
				</tr>
				
			</c:forEach>
			
		</table>
	</body>
</html>