package cs3220.servlet.homework02;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework02.model.File;

/**
 * Servlet implementation class NewFolder
 */
@WebServlet("/NewFolder")
public class NewFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("<html>"
				+		"<head><title></title></head>"
				+ 		"<body>"
				+ 			"<form method='post' action='NewFolder'>"
				+ 				"New Folder: <input type='text' name='name'/> "
				+				"<input type='hidden' name='currentFolderId' value='"+request.getParameter("currentFolderId")+"'>"
				+				"<input type='hidden' name='parentFolderId' value='"+request.getParameter("parentFolderId")+"'>"
				+ 				"<input type='submit' value='Create'/>"
				+ 			"</form>"
				+ 		"</body>"
				+	"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentFolderId=Integer.parseInt(request.getParameter("currentFolderId"));
		int parentFolderId=Integer.parseInt(request.getParameter("parentFolderId"));
		ServletContext context=getServletContext();
		@SuppressWarnings("unchecked")
		Map<Integer, Map<Integer,File>> map=(Map<Integer, Map<Integer, File>>) context.getAttribute("homework02.map");
		int id=(int) context.getAttribute("homework02.id");
		String folderName=request.getParameter("name").trim();
		
		if(map.containsKey(currentFolderId))
			 map.get(currentFolderId).put(++id, 
	            		new File(id, folderName, 
	            				null, 0, map.get(parentFolderId).get(currentFolderId), true));
		else{
			Map<Integer, File> internalMap=new HashMap<>();
			internalMap.put(++id, new File(id, folderName, null, 0, map.get(parentFolderId).get(currentFolderId), true));
			map.put(currentFolderId, internalMap);
		}
		
		context.setAttribute("homework02.id", id);
		response.sendRedirect("HomePage"+( currentFolderId!=0 ? "?currentFolderId="+currentFolderId+"&parentFolderId="+parentFolderId : ""));
	}

}
