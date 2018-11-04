package cs3220.guestBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyGuestBook
 */
@WebServlet("/MyGuestBook")
public class MyGuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		List<DataModel> arr=new ArrayList<>();
		arr.add(new DataModel("John", "Hello!"));
		arr.add(new DataModel("Jane", "Your website is nice."));
		getServletContext().setAttribute("dataList", arr);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<DataModel> list=(List<DataModel>) getServletContext().getAttribute("dataList");
		PrintWriter out=response.getWriter();
		out.println("<html>"
				+ 		"<head>"
				+ 			"<title></title>"
				+ 		"</head>"
				+ 		"<body style='text-align:center;'>"
				+ 			"<h1>My Guest Book</h1>"
				+ 			"<table style='margin: auto; border-collapse:collapse; border: 1px solid black;'>");
		for(int i=0;i<list.size();i++){
			DataModel dm=list.get(i);
			out.println("<tr>"
					+ 		"<td style='padding: 2pt;border: 1px solid black;'>"
					+ 			dm.getName()+" says:"
					+ 		"</td>"
					+ 		"<td style='padding: 2pt;border: 1px solid black;'>"
					+ 			dm.getComment()
					+ 		"</td>"
					+ 		"<td style='padding: 2pt;border: 1px solid black;'>"
					+ 			"<a href='EditEntry?id="+i+"'>Edit</a> | <a href='DeleteEntry?id="+i+"'>Delete</a>"
					+ 		"</td>"
					+ "</tr>");
		}
		out.println( 		"</table>"
				+ 			"</br>"
				+ 			"<a href='AddComment'>Add Comment</a>"
				+ 		"</body>"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
