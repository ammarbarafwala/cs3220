<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="cs3220" uri="http://cs.calstatela.edu/cs3220/stu31" %>
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
			<a href="Logout04">Logout</a>
			<p>
				<fmt:parseNumber type="number" var="currentFolderId" value="0"/>
				<fmt:parseNumber type="number" var="parentFolderId" value="0"/>
				<c:if test="${param.currentFolderId!=null && param.currentFolderId!=0}">
				
					<fmt:parseNumber type="number" var="currentFolderId" value="${param.currentFolderId}"/>
					<fmt:parseNumber type="number" var="parentFolderId" value="${param.parentFolderId}"/>
					<c:set value="${homework04Map[parentFolderId][currentFolderId]}" var="currentFolder"/>
					<c:set value="${ currentFolder.name }" var="r"/>
					
					<cs3220:doWhile>
						<cs3220:condition value="${ currentFolder.parent!=null }"/>
						<c:set value="${ currentFolder.parent }" var="currentFolder"/>
						<c:set var="back" value="?currentFolderId=${ currentFolder.id }
								&parentFolderId=${currentFolder.parent!=null?currentFolder.parent.id:0}"/>
						<c:set var="r"> <a href="HomePage04${back}">${currentFolder.name}</a> >> ${ r }</c:set>
						
					</cs3220:doWhile>
					
					<a href="HomePage04">Home</a>${ r }
					<br><br>
					
				</c:if>
				<c:set var="path1" value="?currentFolderId=${currentFolderId}&parentFolderId=${parentFolderId}"/>
				<a class="newfolder" href="NewFolder04${path1}">New Folder</a>
				 | <a class="upload" href="UploadFile04${path1}">Upload</a>
			</p>
		</header>
		
		<table>
		
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Size</th>
				<th>Operations</th>
			</tr>
			
			<c:forEach var="list" items="${homework04Map[currentFolderId]}">
			
				<c:set var="path2" value="?currentFolderId=${list.value.id}&parentFolderId=${currentFolderId}"/>
				<tr>
					<c:choose>
						<c:when test="${list.value.folder}">
							<td><a href="HomePage04${path2}">${list.value.name}</a></td>
							<td><fmt:formatDate value="${list.value.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td></td>
						</c:when>
				 		<c:otherwise>
				 			<td><a href="DownloadFile04${path2}">${list.value.name}</a></td>
				 			<td><fmt:formatDate value="${list.value.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td><c:choose>
									<c:when test="${list.value.size<1024}">${list.value.size} B</c:when>
									<c:otherwise><fmt:parseNumber integerOnly="true" value="${list.value.size/1024}"/> KB</c:otherwise>
							</c:choose></td>
				 		</c:otherwise>
				 	</c:choose>
					<td>
						<a href="Delete04${path2}">Delete</a> | 
						<a href="Rename04${path2}">Rename</a>
					</td>
				</tr>
				
			</c:forEach>
			
		</table>
	</body>
</html>