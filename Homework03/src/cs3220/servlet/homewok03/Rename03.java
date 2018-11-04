package cs3220.servlet.homewok03;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework03.model.File;


@WebServlet("/Rename03")
public class Rename03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=((Map<Long, Map<Long,File>>)getServletContext().getAttribute("homework03Map"))
				.get(Long.parseLong(request.getParameter("currentFolderId")))
				.get(Long.parseLong(request.getParameter("folderId"))).getName();
		request.setAttribute("name", name);
		request.getRequestDispatcher("/WEB-INF/Rename03.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long currentFolderId=Long.parseLong(request.getParameter("folderId"));
		long parentFolderId=Long.parseLong(request.getParameter("currentFolderId"));
		@SuppressWarnings("unchecked")
		Map<Long, Map<Long,File>> map=(Map<Long, Map<Long, File>>) getServletContext().getAttribute("homework03Map");
		String folderName=request.getParameter("name").trim();
		File currentFolder=map.get(parentFolderId).get(currentFolderId);
		currentFolder.setName(folderName);
		request.getRequestDispatcher("HomePage03").forward(request, response);
	}

}
