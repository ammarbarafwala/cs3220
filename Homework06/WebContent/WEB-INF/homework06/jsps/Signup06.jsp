<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("input[name='username']").blur(function() {
			var $this=$(this);
			$this.next().remove("div");
			 $.ajax({
	              url: "UsernameCheck06",
	              data: {
	                  "userName": $this.val()
	              },
	              success: function(data){
	            	  if(data!=""){
	            		  $("input[type='submit']").click(function() {
	            				event.preventDefault();
	            			});
	            		  $this.after("<div>"+data+"</div>");
	            	  }
	            	  else{
	            		  $("input[type='submit']").off("click");
	            	  }
	              }
			 });
		});
	});
</script>
</head>
<body>
	<h2>Sign Up Page</h2>
	<p><a href="Login06">Login</a></p>
	${ error }
	<form action="Signup06" method="post">
		<p>Username: <input type="text" name="username"></p>
		<p>Password: <input type="password" name="password"></p>
		<input type="submit" value="Create Account">
	</form>
</body>
</html>