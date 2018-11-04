package cs3220.guestBook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditEntry
 */
@WebServlet("/EditEntry")
public class EditEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		DataModel dm=((List<DataModel>)getServletContext().getAttribute("dataList")).get(id);
		response.getWriter().println("<html>"
				+ 						"<title><head></head></title>"
				+ 						"<body>"
				+ 						"<form action='EditEntry' method='post'>"
				+						"<h1>Edit Entry</h1>"
				+ 							"Your name: <input type='text' name='name' value='"+dm.getName()+"'/>"
				+ 							"<input type='textbox' name='comment' value='"+dm.getComment()+"'/>"
				+							"<input type='hidden' name='id' value='"+id+"'/>"
				+							"<input type='submit' value='Edit'/>"
				+ 						"</form>"
				+ 						"</body>"
				+ 					"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		DataModel dm=((List<DataModel>)getServletContext().getAttribute("dataList")).get(id);
		dm.setName(request.getParameter("name"));
		dm.setComment(request.getParameter("comment"));
		response.sendRedirect("MyGuestBook");
	}

}
