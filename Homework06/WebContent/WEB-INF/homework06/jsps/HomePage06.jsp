<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
	<meta charset="ISO-8859-1">
	<title>Online File Manager</title>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		$(function() {
			
			function deleteEntry(){
				event.preventDefault();
			       var row = $(this).closest("tr");
			       $.ajax({
			           url: "Delete06",
			           method: "POST",
			           data: {
			               "id" : row.attr("data-entry-id"),
			               "isFolder" : row.attr("data-entry-isfolder")
			           },
			           success: function(){
			               row.remove();
			           }
			       });
			}
			$(".delete").click(deleteEntry);
			
			$(".newfolder").click(function() {
				event.preventDefault();
				$(this).siblings(".hide").toggle();
				
			});
			$("input[value='Create']").click(function() {
				var $this=$(this);
				var folderName=$this.prev().val();
				
				$.ajax({
					url: "NewFolder06",
					method: "POST",
					data: {
						"folderName": folderName,
						"id" : $this.parent().attr("data-entry-id")
					},
					success: function(data) {
						var obj = JSON.parse(data);
						var tableLocation=$("tr[data-entry-isfolder='true']:last").length==0?$("tr:first"):$("tr[data-entry-isfolder='true']:last");
						tableLocation.after($("<tr data-entry-id="+obj["id"]+" data-entry-isfolder='true'>"
							+"<td><a href='HomePage06?id="+obj["id"]+"'>"+folderName+"</a></td>"
							+"<td>"+obj["date"]+"</td>"
							+"<td></td>"
						+"</tr>").append($("<td>"
								+" | <a href='Rename06?id="+obj["id"]+"&parentId="+obj["parentId"]+"&name="+folderName+"'>Rename</a></td>")
								.prepend($("<a href='#'>Delete</a>").click(deleteEntry))));
					}
				});
			});
			$("a[id='toggleShare']").click(function() {
				event.preventDefault();
				var select=$(this).prev().find("select");
				select.prev().remove("div[class='text']");
				 $.ajax({
		              url: "ShareFile06",
		              success: function(data){ 
		            	var obj = JSON.parse(data);
		            	obj.forEach(function (a) {
							select.append("<option value="+a["id"]+">"+a["username"]+"</option>");
							console.log(a["username"]);
						});
		              }
				 });
				$(this).prev().toggle();
			});
			$("button[id='share']").click(function() {
				var select=$(this).prev();
				select.prev().remove("div[class='text']");
				 $.ajax({
		              url: "ShareFile06",
		              method : "POST",
		              data: {
		                  "userId": select.val(),
		                  "fileId"	: select.closest("tr").attr("data-entry-id")
		              },
		              success: function(data){
		                  select.before("<div class='text'>"+data+"</div>");
		              }
				 });
			});
			
		});
	</script>
	</head>
	<body>
		<header>
			<h2>Online File Manager</h2>
			<p><a href="Logout06">Logout</a></p>
			
				<c:set var="i" value="${ fn:length(homework06BackList)}"/>
				<a href="HomePage06">Home</a>
				<c:forEach begin="1" end="${ fn:length(homework06BackList)}" varStatus="status">
					>> <c:if test="${ !status.last }">
						<a href="HomePage06?id=${ homework06BackList[i-status.index].id }">${ homework06BackList[i-status.index].name }</a> 
					</c:if>
				</c:forEach>
				${ homework06BackList[0].name }
					<br><br>
				<c:set var="path1" value="?id=${ homework06BackList[0]==null?0:homework06BackList[0].id }"/>
				<c:if test="${ homework06BackList[0].id != 1 }">
					<a class="newfolder" href="#">New Folder</a>
					 | <a class="upload" href="UploadFile06${path1}">Upload</a>
					 <p class="hide" style="display:none;" data-entry-id="${ homework06BackList[0]==null?0:homework06BackList[0].id }">
					 	New Folder: <input type='text' name='name'>
						<input type='submit' value='Create'>
					</p>
				 </c:if>
		</header>
		
		<table>
		
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Size</th>
				<th>Operations</th>
			</tr>
			
			<c:forEach var="file" items="${homework06List}">
			
				<c:set var="path2" value="?id=${ file.id }"/>
				<tr data-entry-id="${ file.id }" data-entry-isfolder="${ file.folder }">
					<c:choose>
						<c:when test="${ file.id == 1 }">
							<td><a href="HomePage06${path2}">${file.name}</a></td>
							<td><fmt:formatDate value="${file.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td></td><td></td>
						</c:when>
						<c:when test="${file.folder}">
							<td><a href="HomePage06${path2}">${file.name}</a></td>
							<td><fmt:formatDate value="${file.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td></td>
							<td>
								<a class="delete" href="#">Delete</a> | 
								<a href="Rename06${path2}&parentId=${ file.parentId }&name=${ file.name }">Rename</a>
							</td>
						</c:when>
				 		<c:otherwise>
				 			<td><a href="DownloadFile06${path2}">${file.name}</a></td>
				 			<td><fmt:formatDate value="${file.date}" pattern="MM/dd/yyyy hh:mm a"/></td>
							<td><c:choose>
									<c:when test="${file.size<1024}">${file.size} B</c:when>
									<c:otherwise><fmt:parseNumber integerOnly="true" value="${file.size/1024}"/> KB</c:otherwise>
							</c:choose></td>
							<td>
								<div class="hide" style="display:none;">
									<select></select>
									<button id="share">Share</button>
								</div>
								<a id="toggleShare" href="#">Share</a> |
								<a class="delete" href="#">Delete</a> | 
								<a href="Rename06${path2}&parentId=${ file.parentId }&name=${ file.name }">Rename</a>
							</td>
				 		</c:otherwise>
				 	</c:choose>
				</tr>
				
			</c:forEach>
			
		</table>
	</body>
</html>