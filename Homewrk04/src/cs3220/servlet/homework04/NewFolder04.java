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

/**
 * Servlet implementation class NewFolder04
 */
@WebServlet("/NewFolder04")
public class NewFolder04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("homework04Map")==null)
			response.sendRedirect("HomePage04");
		else
			request.getRequestDispatcher("/WEB-INF/jsp/NewFolder04.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long currentFolderId=Long.parseLong(request.getParameter("currentFolderId"));
		long parentFolderId=Long.parseLong(request.getParameter("parentFolderId"));
		ServletContext context=getServletContext();
		@SuppressWarnings("unchecked")
		Map<Long, Map<Long,File>> map=(Map<Long, Map<Long, File>>) request.getSession().getAttribute("homework04Map");
		long id=(long) context.getAttribute("homework04Id");
		String folderName=request.getParameter("name").trim();
		
		if(map.containsKey(currentFolderId))
			 map.get(currentFolderId).put(++id, 
	            		new File(id, folderName, 
	            				null, 0, map.get(parentFolderId).get(currentFolderId), true));
		else{
			Map<Long, File> internalMap=new HashMap<>();
			internalMap.put(++id, new File(id, folderName, null, 0, map.get(parentFolderId).get(currentFolderId), true));
			map.put(currentFolderId, internalMap);
		}
		context.setAttribute("homework04Id", id);
		response.sendRedirect("HomePage04"+( currentFolderId!=0 ? "?currentFolderId="+currentFolderId+"&parentFolderId="+parentFolderId : ""));
	}

}
