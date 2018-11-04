package cs3220.servlet.midterm01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.midterm01.model.FacultyDetails;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/MainPage")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
    public void init() throws ServletException {
    	List<FacultyDetails> list=new ArrayList<>();
    	FacultyDetails fa=new FacultyDetails("Computer Science");
    	fa.getFacultyNameList().add("Pamula(Chair)");
    	fa.getFacultyNameList().add("Sun");
    	list.add(fa);
    	fa=new FacultyDetails("Electrical and Computer Engineering");
    	fa.getFacultyNameList().add("Agarwal");
    	list.add(fa);
    	getServletContext().setAttribute("midterm01List", list);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
