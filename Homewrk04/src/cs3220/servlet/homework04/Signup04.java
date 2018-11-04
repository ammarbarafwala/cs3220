package cs3220.servlet.homework04;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework04.model.File;
import cs3220.servlet.homework04.model.UserDetails;

/**
 * Servlet implementation class Signup04
 */
@WebServlet("/Signup04")
public class Signup04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/Signup04.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		ServletContext sc=getServletContext();
		Map<String, UserDetails> loginMap=(Map<String, UserDetails>)sc.getAttribute("homework04LoginMap");
		if(loginMap.containsKey(username)|| password==null ||password.trim().isEmpty()){
			request.setAttribute("error", "<p>Username already present/Password is empty.</p>");
			request.getRequestDispatcher("/WEB-INF/jsp/Signup04.jsp").forward(request, response);
		}
		else{
			Map<Long, Map<Long,File>> map=new HashMap<>();
			Map<Long,File> internalMap=new HashMap<>();
			long id= (long) sc.getAttribute("homework04Id");
			System.out.println(id);
			map.put( 0L, internalMap);
			internalMap.put(++id, new File(id, "Documents", "", 0, null, true));
			System.out.println(internalMap.get(id));
			internalMap.put(++id, new File(id, "My Files", "", 0, null, true));
			internalMap.put(++id, new File(id, "Temp", "", 0, null, true));
			sc.setAttribute("homework04Id", id);
			loginMap.put(username, new UserDetails(username, password, map));
			request.getSession().setAttribute("homework04Map", map);
			response.sendRedirect("HomePage04");
			
		}
	}

}
