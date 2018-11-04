package cs3220.servlet.lab12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import cs3220.servlet.lab12.model.Question;

/**
 * Servlet implementation class DrivingTestBrowser
 */
@WebServlet("/DrivingTestBrowser")
public class DrivingTestBrowser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext();
		List<Question> list=new ArrayList<>();
		try {
			Scanner sc=new Scanner(new File(context.getRealPath("/WEB-INF/DrivingTest.txt")));
			while(sc.hasNextLine()){
				list.add(new Question(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine())));
				sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.setAttribute("lab12List", list);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexString=request.getParameter("index");
		List<Question> list=(List<Question>) getServletContext().getAttribute("lab12List");
		int index=0;
		if(indexString!=null)
			index=Integer.parseInt(indexString);
		else{
			HttpSession session=request.getSession(false);
			if(session!=null)
				session.invalidate();
			session=request.getSession();
			session.setAttribute("lab12Result", new ArrayList<>());
		}
		
		if(index>list.size()-1){
			request.getRequestDispatcher("Result.jsp").forward(request, response);
		}
		else{
			request.setAttribute("question", list.get(index));
			request.setAttribute("index", index);
			request.getRequestDispatcher("/WEB-INF/Display.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String choice=request.getParameter("choice");
		
		if(choice!=null){
			HttpSession session=request.getSession(false);
			List<Question> list=(List<Question>) getServletContext().getAttribute("lab12List");
			((List<String>)session.getAttribute("lab12Result")).add(choice);
			
			int index=Integer.parseInt(request.getParameter("index"));
			
			if(index>list.size()-1)
				request.getRequestDispatcher("/WEB-INF/DrivingResult.jsp").forward(request, response);
			else{
				request.setAttribute("question", list.get(index));
				request.setAttribute("index", index);
				request.getRequestDispatcher("/WEB-INF/Display.jsp").forward(request, response);
			}
		}
		else
			response.sendRedirect("DrivingTestBrowser?index="+(Integer.parseInt(request.getParameter("index"))-1)+"");
	}

}
