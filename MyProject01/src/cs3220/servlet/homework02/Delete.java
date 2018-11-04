package cs3220.servlet.homework02;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework02.model.File;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<Integer, Map<Integer,File>> map=(Map<Integer, Map<Integer, File>>) getServletContext().getAttribute("homework02.map");
		int currentFolderId=Integer.parseInt(request.getParameter("currentFolderId"));
		int parentFolderId=Integer.parseInt(request.getParameter("parentFolderId"));
		File currentFolder=map.get(parentFolderId).remove(currentFolderId);
		if(currentFolder.isFolder())
			delete(map, currentFolderId);
		else{
			java.io.File file=new java.io.File(getServletContext().getRealPath("/WEB-INF/files/"+currentFolder.getId()));
			file.delete();
		}
        response.sendRedirect("HomePage"+( parentFolderId!=0 ? "?currentFolderId="+parentFolderId+"&parentFolderId="
        						+(currentFolder.getParent().getParent()!=null? currentFolder.getParent().getParent().getId():0) : ""));
	}

	//deleting All sub-folders
	private void delete(Map<Integer, Map<Integer, File>> map, int currentFolderId){
		if(map.containsKey(currentFolderId)){
			
			for(Integer key:map.get(currentFolderId).keySet()){
				File currentFolder=map.get(currentFolderId).get(key);
				
				if(currentFolder.isFolder())
					delete(map, currentFolder.getId());
				else
					new java.io.File(getServletContext().getRealPath("/WEB-INF/files/"+currentFolder.getId())).delete();
			}
			map.remove(currentFolderId);
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
