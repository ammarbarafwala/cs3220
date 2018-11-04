package cs3220.servlet.homework02;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework02.model.File;


@WebServlet("/Rename")
public class Rename extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentFolderId=request.getParameter("currentFolderId");
		String parentFolderId=request.getParameter("parentFolderId");
		response.getWriter().print("<html>"
				+		"<head><title></title></head>"
				+ 		"<body>"
				+ 			"<form method='post' action='Rename'>"
				+ 				"Edit Name: <input type='text' name='name' "
				+					"value='"+((Map<Integer, Map<Integer,File>>)getServletContext().getAttribute("homework02.map"))
												.get(Integer.parseInt(parentFolderId)).get(Integer.parseInt(currentFolderId)).getName()+"'/> "
				+				"<input type='hidden' name='currentFolderId' value='"+currentFolderId+"'>"
				+				"<input type='hidden' name='parentFolderId' value='"+parentFolderId+"'>"
				+ 				"<input type='submit' value='Save'/>"
				+ 			"</form>"
				+ 		"</body>"
				+	"</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentFolderId=Integer.parseInt(request.getParameter("currentFolderId"));
		int parentFolderId=Integer.parseInt(request.getParameter("parentFolderId"));
		@SuppressWarnings("unchecked")
		Map<Integer, Map<Integer,File>> map=(Map<Integer, Map<Integer, File>>) getServletContext().getAttribute("homework02.map");
		String folderName=request.getParameter("name").trim();
		File currentFolder=map.get(parentFolderId).get(currentFolderId);
		currentFolder.setName(folderName);
		response.sendRedirect("HomePage"+( parentFolderId!=0 ? "?currentFolderId="+parentFolderId+"&parentFolderId="
								+(currentFolder.getParent().getParent()!=null? currentFolder.getParent().getParent().getId():0) : ""));
	}

}
