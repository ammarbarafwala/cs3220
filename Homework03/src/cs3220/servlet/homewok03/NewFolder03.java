package cs3220.servlet.homewok03;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework03.model.File;

/**
 * Servlet implementation class NewFolder03
 */
@WebServlet("/NewFolder03")
public class NewFolder03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/NewFolder03.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long currentFolderId=Long.parseLong(request.getParameter("currentFolderId"));
		long parentFolderId=Long.parseLong(request.getParameter("parentFolderId"));
		ServletContext context=getServletContext();
		@SuppressWarnings("unchecked")
		Map<Long, Map<Long,File>> map=(Map<Long, Map<Long, File>>) context.getAttribute("homework03Map");
		int id=(int) context.getAttribute("homework03Id");
		String folderName=request.getParameter("name").trim();
		
		if(map.containsKey(currentFolderId))
			 map.get(currentFolderId).put((long) ++id, 
	            		new File(id, folderName, 
	            				null, 0, map.get(parentFolderId).get(currentFolderId), true));
		else{
			Map<Long, File> internalMap=new HashMap<>();
			internalMap.put((long) ++id, new File(id, folderName, null, 0, map.get(parentFolderId).get(currentFolderId), true));
			map.put(currentFolderId, internalMap);
		}
		context.setAttribute("homework03Id", id);
		request.getRequestDispatcher("/WEB-INF/HomePage03.jsp").forward(request, response);
	}

}
