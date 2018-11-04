package cs3220.servlet.homework04;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework04.model.File;


@WebServlet("/Rename04")
public class Rename04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("homework04Map")==null)
			response.sendRedirect("HomePage04");
		else{
			String name=((Map<Long, Map<Long,File>>)request.getSession().getAttribute("homework04Map"))
					.get(Long.parseLong(request.getParameter("parentFolderId")))
					.get(Long.parseLong(request.getParameter("currentFolderId"))).getName();
			request.setAttribute("name", name);
			request.getRequestDispatcher("/WEB-INF/jsp/Rename04.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long currentFolderId=Long.parseLong(request.getParameter("currentFolderId"));
		long parentFolderId=Long.parseLong(request.getParameter("parentFolderId"));
		@SuppressWarnings("unchecked")
		Map<Long, Map<Long,File>> map=(Map<Long, Map<Long, File>>) request.getSession().getAttribute("homework04Map");
		String folderName=request.getParameter("name").trim();
		File currentFolder=map.get(parentFolderId).get(currentFolderId);
		currentFolder.setName(folderName);
        response.sendRedirect("HomePage04"+( parentFolderId!=0 ? "?currentFolderId="+parentFolderId+"&parentFolderId="
        						+(currentFolder.getParent().getParent()!=null? currentFolder.getParent().getParent().getId():0) : ""));
	}
}
