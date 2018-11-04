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

@WebServlet("/HomePage04")
public class HomePage04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		ServletContext sc=getServletContext();
		Map<Long, Map<Long,File>> map=new HashMap<>();
		Map<Long,File> internalMap=new HashMap<>();
		long id= 0L;
		map.put( id, internalMap);
		internalMap.put(++id, new File(id, "Documents", "", 0, null, true));
		internalMap.put(++id, new File(id, "My Files", "", 0, null, true));
		internalMap.put(++id, new File(id, "Temp", "", 0, null, true));
		Map<String, UserDetails> loginMap=new HashMap<>();
		loginMap.put("cysun", new UserDetails("cysun", "abcd", map));
		sc.setAttribute("homework04LoginMap", loginMap);
		sc.setAttribute("homework04Id", id);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("homework04Map")==null){
			request.setAttribute("error", request.getParameter("error"));
			request.getRequestDispatcher("/WEB-INF/jsp/Login04.jsp").forward(request, response);
		}
		else
			request.getRequestDispatcher("/WEB-INF/jsp/HomePage04.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDetails user=((Map<String, UserDetails>)getServletContext().getAttribute("homework04LoginMap")).get(request.getParameter("username"));
		if(user!=null){
			if(user.getPassword().equals(request.getParameter("password"))){
				request.getSession().setAttribute("homework04Map", user.getMap());
				response.sendRedirect("HomePage04");
			}
			else
				response.sendRedirect("HomePage04?error=Invalid Password");
		}
		else
			response.sendRedirect("HomePage04?error=Invalid Username");
	}

}
