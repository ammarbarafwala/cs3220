package cs3220.guestBook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().println("<html>"
				+ 						"<title><head></head></title>"
				+ 						"<body>"
				+ 						"<form action='AddComment' method='post'>"
				+						"<h1>Add Comment</h1>"
				+ 							"Your name: <input type='text' name='name'/>"
				+ 							"<input type='textbox' name='comment'/>"
				+							"<input type='submit' value='Add'/>"
				+ 						"</form>"
				+ 						"</body>"
				+ 					"</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<DataModel> list=(List<DataModel>) getServletContext().getAttribute("dataList");
		list.add(new DataModel(request.getParameter("name"), request.getParameter("comment")));
		response.sendRedirect("MyGuestBook");
	}

}
