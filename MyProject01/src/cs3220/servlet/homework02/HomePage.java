package cs3220.servlet.homework02;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework02.model.File;

@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		Map<Integer, Map<Integer,File>> map=new HashMap<>();
		Map<Integer,File> internalMap=new HashMap<>();
		ServletContext sc=getServletContext();
		int id=0;
		internalMap.put(++id, new File(id, "Documents", "", 0, null, true));
		internalMap.put(++id, new File(id, "My Files", "", 0, null, true));
		internalMap.put(++id, new File(id, "Temp", "", 0, null, true));
		map.put(0, internalMap);
		sc.setAttribute("homework02.map", map);
		sc.setAttribute("homework02.id", id);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String currentFolderIdAsString=request.getParameter("currentFolderId");
		int currentFolderId=0,parentFolderId=0;
		PrintWriter out=response.getWriter();
		@SuppressWarnings("unchecked")
		Map<Integer, Map<Integer,File>> map=(Map<Integer, Map<Integer, File>>) getServletContext().getAttribute("homework02.map");
		out.print("<html><head><link rel='stylesheet' type='text/css' href='style.css'></head><body>");
		
		if(currentFolderIdAsString!=null){
			currentFolderId=Integer.parseInt(currentFolderIdAsString);
			parentFolderId=Integer.parseInt(request.getParameter("parentFolderId"));
			File currentFile=map.get(parentFolderId).get(currentFolderId);
			String path=currentFile.getParent()!=null?"?currentFolderId="+parentFolderId+"&parentFolderId="
						+(currentFile.getParent().getParent()!=null?currentFile.getParent().getParent().getId():0):"";
			out.print("<a href='HomePage"+path+"'>..</a>\\"
					+	map.get(parentFolderId).get(currentFolderId).getName());
		}
		
		out.print(	" [<a href='NewFolder?currentFolderId="+currentFolderId+"&parentFolderId="+parentFolderId+"'>New Folder</a>"
				+ 	" | <a href='UploadFile?currentFolderId="+currentFolderId+"&parentFolderId="+parentFolderId+"'>Upload</a>]<br><br>"
				+ 	"<table>"
				+ 		"<tr>"
				+ 			"<th>Name</th>"
				+ 			"<th>Date</th>"
				+			"<th>Size</th>"
				+			"<th>Operations</th>"
				+ 		"</tr>");
		
		Map<Integer, File> internalMap=map.get(currentFolderId);
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		if(internalMap!=null){
			for(Integer key:internalMap.keySet()){
			
				File f=internalMap.get(key);
				
				if(f.isFolder())
					out.print(	"<tr>"
							+ 		"<td><a href='HomePage?currentFolderId="+f.getId()+"&parentFolderId="+currentFolderId+"'>"+f.getName()+"</a></td>"
							+ 		"<td>"+sdf.format(f.getDate())+"</td>"
							+ 		"<td></td>");
				else
					out.print(	"<tr>"
							+ 		"<td><a href='DownloadFile?currentFolderId="+f.getId()+"&parentFolderId="+currentFolderId+"'>"+f.getName()+"</a></td>"
							+ 		"<td>"+sdf.format(f.getDate())+"</td>"
							+ 		"<td>"+(f.getSize()<1024?f.getSize()+" B":f.getSize()/1024+" KB")+"</td>");
				
				out.print(			"<td><a href='Rename?currentFolderId="+f.getId()+"&parentFolderId="+currentFolderId+"'>Rename</a>"
							+ 			" | <a href='Delete?currentFolderId="+f.getId()+"&parentFolderId="+currentFolderId+"'>Delete</a></td>"
							+ 	"</tr>");
			}
		}
		out.print("</table></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
