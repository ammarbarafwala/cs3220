<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3><a href="DisplayFaculty">ECST Faculty</a> &gt; Edit Faculty</h3>
<form action="EditFaculty" method="post">
<table border="1">
  <tr>
    <th>Department:</th>
    <td>
      <select name="department" >
        <c:forEach items="${departments}" var="department" >
        <c:choose>
        	<c:when test="${ department.name==departmentName }">
        		 <option selected="selected">${department.name}</option>
        	</c:when>
	        <c:otherwise>
	        	 <option>${department.name}</option>
	        </c:otherwise>
        </c:choose>
        </c:forEach>
      </select>
    </td>
  </tr>
  <tr>
    <th>Name:</th>
    <td><input type="text" name="faculty" value="${ faculty.name }" /></td>
  </tr>
  <tr>
    <th>Chairperson:</th>
      <td><c:choose>
        	<c:when test="${faculty.chair}">
        		 <input type="checkbox" name="chair" checked="checked" />
        	</c:when>
        <c:otherwise>
        	 <input type="checkbox" name="chair" />
        </c:otherwise>
       </c:choose></td></tr>
  <tr>
    <td colspan="2">
      <input type="submit" name="edit" value="Edit" />
    </td>
  </tr>
</table>
<input type="hidden" value="${ faculty.id }" name="id">
</form>
</body>
</html>