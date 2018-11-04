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
 * Servlet implementation class OnlineFileManager
 */
@WebServlet("/OnlineFileManager")
public class OnlineFileManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		int i = 0;
		ServletContext sc=getServletContext();
		Map<Integer, Map<String,Folder>> map = new HashMap<>();
		Map<String,Folder> tempMap=new HashMap<>();
		tempMap.put("Documents", new Folder(++i, "Documents", null));
		tempMap.put("My Files", new Folder(++i, "My Files", null));
		tempMap.put("Temp", new Folder(++i, "Temp", null));
		map.put(0, tempMap);
		sc.setAttribute("homework01.id", i);
		sc.setAttribute("homework01.map", map);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int parentId = 0;
		PrintWriter out=response.getWriter();
		Map<Integer, Map<String,Folder>> map=(Map<Integer, Map<String, Folder>>) getServletContext().getAttribute("homework01.map");
		String currentName = request.getParameter("currentName");
		Folder currentFolder=null;
		out.print("<html>"
				+ 		"<head><title></title></head>"
				+ 		"<body>");
		
		//For displaying the current Folder Name and Options
		if(currentName!=null){
			parentId=Integer.parseInt(request.getParameter("parentId"));
			currentFolder=map.get(parentId).get(currentName);
			out.print(currentFolder.getName()
					+	" [<a href='CreateFolder?currentName="+ currentName+"&parentId="+parentId+"'>New Folder</a>"
					+	" | <a href='EditFolder?currentName="+ currentName+"&parentId="+parentId+"'>Edit Folder</a>"
					+ 	" | <a href='DeleteFolder?currentName="+ currentName+"&parentId="+parentId+"'>Delete Folder</a>]<br><br>");
			//for back button
			if(currentFolder.getParent()!=null){
				int gpId=currentFolder.getParent().getParent()!=null?currentFolder.getParent().getParent().getId():0;
				out.print("\\ <a href='OnlineFileManager?currentName="+currentFolder.getParent().getName()+"&parentId="+gpId+"'>..</a><br>");
			}
			else
				out.print("\\ <a href='OnlineFileManager'>..</a><br>");
		}
		else
			out.print("[<a href='CreateFolder?parentId=0'>New Folder</a>]<br><br>");
		
		//for displaying sub-folder names
		Map<String, Folder> innerMap;
		int currentId=currentFolder==null?0:currentFolder.getId();
		if((innerMap=map.get(currentId))!=null)
			for(String s:innerMap.keySet())
				out.println("\\ <a href='OnlineFileManager?currentName="+s+"&parentId="+currentId+"'>"+s+"</a><br>");
		
		out.print(		"</body>"
				+ 	"</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
