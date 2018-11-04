<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Currency Converter</title>
</head>
<body>
<form action='CurrencyConverter' method='post'>
<input type="number" name="amount"/>
<select name="c1">
 <c:forEach items="${data}" var="number">
        <option>${number.key}</option>
  </c:forEach>
</select>
?=
<select name="c2">
 <c:forEach items="${data}" var="number">
        <option>${number.key}</option>
  </c:forEach>
</select>
<input type="submit" value="Convert" name="convert">
</form>
</body>
</html>