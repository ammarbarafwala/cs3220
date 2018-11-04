package cs3220.servlet.homework01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework01.model.Folder;

/**
 * Servlet implementation class CreateFolder
 */
@WebServlet("/CreateFolder")
public class CreateFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage=request.getParameter("errorMessage")==null?"":"<br>"+request.getParameter("errorMessage")+"<br>";
		PrintWriter out=response.getWriter();
		out.print("<html>"
				+		"<head><title></title></head>"
				+ 		"<body>"
				+ 			"<form method='post' action='CreateFolder'>"
				+ 				"New Folder: <input type='text' name='name'/> ");
		if(request.getParameter("currentName")!=null)
			out.print( 			"<input type='hidden' name='currentName' value='"+request.getParameter("currentName")+""+"'>");
		out.print( 				errorMessage
				+ 				"<input type='hidden' name='parentId' value='"+request.getParameter("parentId")+"'>"
				+ 				"<input type='submit' value='Create'/>"
				+ 			"</form>"
				+ 		"</body>"
				+	"</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc=getServletContext();
		Map<Integer,Map<String,Folder>> map=(Map<Integer, Map<String, Folder>>) sc.getAttribute("homework01.map");
		int parentId=Integer.parseInt(request.getParameter("parentId"));
		String currentName=request.getParameter("currentName");
		int id=(int) sc.getAttribute("homework01.id");
		int currentId=0;
		Folder current=null;
		String path="",path2="parentId="+parentId+"&&";
		
		if(currentName!=null){
			current=map.get(parentId).get(currentName);
			currentId=current.getId();
			path="?currentName="+currentName.replaceAll(" ", "%20")+"&&parentId="+parentId;
			path2="currentName="+currentName.replaceAll(" ", "%20")+"&&"+path2;	
		}
		String name=request.getParameter("name").trim();
		//Checking if name entered is empty
		if(name.isEmpty())
			response.sendRedirect("CreateFolder?"+path2+"errorMessage=Name field cannot be empty.");
		//checking if folder with same name exists
		else if(map.get(currentId)!=null && map.get(currentId).containsKey(name))
			response.sendRedirect("CreateFolder?"+path2+"errorMessage=A folder with this name already exists.");
		//create folder with the name inserted and map it to its parent
		else{
			
			if(map.containsKey(currentId))
				map.get(currentId).put(name, new Folder(++id, name, current));
			else{
				Map<String, Folder> tempMap=new HashMap<>();
				tempMap.put(name, new Folder(++id, name, current));
				map.put(currentId, tempMap);
			}
			sc.setAttribute("homework01.id",id);
			response.sendRedirect("OnlineFileManager"+path);
		}
			
	}

}
