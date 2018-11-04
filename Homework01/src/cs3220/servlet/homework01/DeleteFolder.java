package cs3220.servlet.homework01;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework01.model.Folder;

/**
 * Servlet implementation class DeleteFolder
 */
@WebServlet("/DeleteFolder")
public class DeleteFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, Map<String,Folder>> map=(Map<Integer, Map<String, Folder>>) getServletContext().getAttribute("homework01.map");
		int parentId=Integer.parseInt(request.getParameter("parentId"));
		Folder current=map.get(parentId).get(request.getParameter("currentName"));
		String path="";
		if(current.getParent()!=null){
			int gpId = current.getParent().getParent()!=null?current.getParent().getParent().getId():0;
			path="?currentName="+current.getParent().getName()+"&&parentId="+gpId;
		}
		delete(map,current.getId());
		map.get(parentId).remove(current.getName());
		response.sendRedirect("OnlineFileManager"+path);
	}

	//deleting All sub-folders
	private void delete(Map<Integer, Map<String, Folder>> map, int currentId){
		if(map.containsKey(currentId)){
			for(String s:map.get(currentId).keySet())
				delete(map, map.get(currentId).get(s).getId());
			map.remove(currentId);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
