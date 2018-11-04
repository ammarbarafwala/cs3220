package cs3220.servlet.homework01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework01.model.Folder;

/**
 * Servlet implementation class EditFolder
 */
@WebServlet("/EditFolder")
public class EditFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage=request.getParameter("errorMessage")==null?"":"<br>"+request.getParameter("errorMessage")+"<br>";
		PrintWriter out=response.getWriter();
		out.print("<html>"
				+		"<head><title></title></head>"
				+ 		"<body>"
				+ 			"<form method='post' action='EditFolder'>"
				+ 				"Edit Folder: <input type='text' name='name' value='"+request.getParameter("currentName")+"'/> "
				+				"<input type='hidden' name='currentName' value='"+request.getParameter("currentName")+"'/>"
				+				errorMessage
				+ 				"<input type='hidden' name='parentId' value='"+request.getParameter("parentId")+"'/>"
				+ 				"<input type='submit' value='Save'/>"
				+ 			"</form>"
				+ 		"</body>"
				+	"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Helloooooooooooooooooooo");
		ServletContext sc=getServletContext();
		Map<Integer,Map<String,Folder>> map=(Map<Integer, Map<String, Folder>>) sc.getAttribute("homework01.map");
		int parentId=Integer.parseInt(request.getParameter("parentId"));
		String currentName=request.getParameter("currentName");
		Folder current=map.get(parentId).get(currentName);
		String path="?currentName="+currentName.replaceAll(" ", "%20")+"&&parentId="+parentId;
		String name=request.getParameter("name").trim();
		//Checking if name entered is empty
		if(name.isEmpty())
			response.sendRedirect("EditFolder"+path+"&&errorMessage=Name field cannot be empty.");
		else if(currentName.equals(name))
			response.sendRedirect("OnlineFileManager"+path);
		//checking if folder with same name exists
		else if(map.get(parentId)!=null && map.get(parentId).containsKey(name))
			response.sendRedirect("EditFolder"+path+"&&errorMessage=A folder with this name already exists.");
		//create folder with the name inserted and map it to its parent
		else{
			map.get(parentId).remove(currentName);
			current.setName(name);
			map.get(parentId).put(current.getName(), current);
			response.sendRedirect("OnlineFileManager?currentName="+name.replaceAll(" ", "%20")+"&&parentId="+parentId);
		}
	}

}
