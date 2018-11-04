package cs3220.servlet.homework04;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework04.model.File;


@WebServlet("/Delete04")
public class Delete04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<Long, Map<Long,File>> map=(Map<Long, Map<Long, File>>) request.getSession().getAttribute("homework04Map");
		
		if(map==null){
			response.sendRedirect("HomePage04");
			return;
		}
		long currentFolderId=Long.parseLong(request.getParameter("currentFolderId"));
		long parentFolderId=Long.parseLong(request.getParameter("parentFolderId"));
		File currentFolder=map.get(parentFolderId).remove(currentFolderId);
		if(currentFolder.isFolder())
			delete(map, currentFolderId);
		else{
			java.io.File file=new java.io.File(getServletContext().getRealPath("/WEB-INF/files/"+currentFolder.getId()));
			file.delete();
		}
        response.sendRedirect("HomePage04"+( parentFolderId!=0 ? "?currentFolderId="+parentFolderId+"&parentFolderId="
        						+(currentFolder.getParent().getParent()!=null? currentFolder.getParent().getParent().getId():0) : ""));
	}

	//deleting All sub-folders
	private void delete(Map<Long, Map<Long, File>> map, long currentFolderId){
		if(map.containsKey(currentFolderId)){
			
			for(Long key:map.get(currentFolderId).keySet()){
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
