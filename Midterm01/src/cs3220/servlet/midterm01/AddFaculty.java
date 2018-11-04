package cs3220.servlet.midterm01;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.midterm01.model.FacultyDetails;

/**
 * Servlet implementation class AddFaculty
 */
@WebServlet("/AddFaculty")
public class AddFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/AddFaculty.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int departmentIndex=Integer.parseInt(request.getParameter("departmentName"));
		String chairperson=request.getParameter("chairperson");
		String name=null;
		if(chairperson==null)
			name=request.getParameter("name");
		else
			name=request.getParameter("name")+" (Chair)";
		List<FacultyDetails> list=(List<FacultyDetails>) getServletContext().getAttribute("midterm01List");
		list.get(departmentIndex).getFacultyNameList().add(name);
		request.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(request, response);
	}

}
