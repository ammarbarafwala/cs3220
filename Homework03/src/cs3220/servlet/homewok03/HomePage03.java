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

@WebServlet("/HomePage03")
public class HomePage03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		Map<Long, Map<Long,File>> map=new HashMap<>();
		Map<Long,File> internalMap=new HashMap<>();
		ServletContext sc=getServletContext();
		int id= 0;
		internalMap.put((long) ++id, new File(id, "Documents", "", 0, null, true));
		internalMap.put((long) ++id, new File(id, "My Files", "", 0, null, true));
		internalMap.put((long) ++id, new File(id, "Temp", "", 0, null, true));
		map.put((long) 0, internalMap);
		sc.setAttribute("homework03Map", map);
		sc.setAttribute("homework03Id", id);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/HomePage03.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
