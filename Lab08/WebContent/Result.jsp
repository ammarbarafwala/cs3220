<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Currency Converter</title>
</head>
<body>
${empty null }
<p>${amount} ${c1} =<fmt:formatNumber type="number" maxIntegerDigits="3"  value="${result}" /> ${c2}</p>
<p><a href="CurrencyConverter">Back</a></p>
</body>
</html>