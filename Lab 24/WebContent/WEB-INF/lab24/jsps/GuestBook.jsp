<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBook</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	
	function deleteEntry(){
	       var row = $(this).closest("tr");
	       $.ajax({
	           url: "AjaxDeleteEntry",
	           data: {
	               "id" : row.attr("data-entry-id")
	           },
	           success: function(){
	               row.remove();
	           }
	       });
	}
	function editEntry(){
	       var td = $(this);
	       var content = td.text();
	       var field = td.attr("data-field");
	       var input = $("<input type='text' />").val(content);
	       input.blur(function(){
	          var value = $(this).val();
	          var row = $(this).closest("tr");
	          
	          $.ajax({
	              url: "AjaxEditEntry",
	              data: {
	                  "id" : row.attr("data-entry-id"),
	                  "field" : field,
	                	"value" : value
	              },
	              success: function(){
	            	  td.empty().text( value );
	              }
	          });
	          
	       });
	       $(this).empty().append(input);
	   }
	
   $(".delete").click(deleteEntry);
   
   $("#add").click(function(){
      var name = $("#name").val();
      var message = $("#message").val();
      if( name != "" && message != "" )
          $.ajax({
              url: "AjaxAddEntry",
              data: {
                  "name": name,
                  "message": message
              },
              success: function(data){
                  var row = $("<tr data-entry-id='" + data + "'></tr>" );
                  var button=$("<button>Delete</button>");
                  var nameBlock = $("<td data-field='name'>" + name + "</td>");
                  var messageBlock=$("<td data-field='message'>" + message + "</td>");
                  row.append(nameBlock)
                     .append(messageBlock)
                     .append($("<td></td>").append(button));
                  button.click(deleteEntry);
                  nameBlock.dblclick(editEntry);
                  messageBlock.dblclick(editEntry);
                  $("#form").before(row);
              }
          });
   });
   
   $("td[data-field]").dblclick(editEntry);
});
</script>
</head>
<body>
	<h2>GuestBook</h2>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Message</th>
			<th></th>
		</tr>
		<c:forEach items="${entries}" var="entry">
			<tr data-entry-id="${entry.id}">
				<td data-field="name">${entry.name}</td>
				<td data-field="message">${entry.message}</td>
				<td><button class="delete">Delete</button></td>
			</tr>
		</c:forEach>
		<tr id="form">
			<td><input id="name" type="text" /></td>
			<td><input id="message" type="text" /></td>
			<td><button id="add">Add</button></td>
	</table>
</body>
</html>