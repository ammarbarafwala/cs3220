package cs3220.servlet.lab09;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListFiles
 */
@WebServlet("/ListFiles")
public class ListFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		ArrayList<Details> arr=new ArrayList<>();
		//arr.add(new Details("text1.txt", new Date(), 12));
		getServletContext().setAttribute("list",arr);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		
		ArrayList<Details> arr=(ArrayList<Details>) getServletContext().getAttribute("list");
		PrintWriter pw=response.getWriter();
		pw.println("<html>"
					+ 	"<head><title></title></head>"
					+ 	"<body>"
					+ 		"<a href='Upload.html'>Upload</a>"
					+ 		"<table style='border-collapse:collapse;border: 1px double black;'>"
					+ 			"<tr >"
					+ 				"<th style='border: 1px solid black;'>Name</th>"
					+ 				"<th style='border: 1px solid black;'>Date Uploaded</th>"
					+ 				"<th style='border: 1px solid black;'>Size</th>"
					+ 				"<th style='border: 1px solid black;'>Operation</th>"
					+ 			"</tr>");
		for(int i=0;i<arr.size();i++){
			Details d=arr.get(i);
			long size=d.getSize();
			String s;
			if(size>=1024)
				s=size/1024+" KB";
			else
				s=size+" B";
			pw.println("<tr>"
					+		"<td style='border: 1px solid black;'><a href='Download?name="+d.getName()+"'>"+d.getName()+"</a></td>"
					+ 		"<td style='border: 1px solid black;'>"+format.format(d.getDate())+"</td>"
					+ 		"<td style='border: 1px solid black;'>"+s+"</td>"
					+ 		"<td style='border: 1px solid black;'><a href='DeleteFile?id="+i+"'>DeleteFile</a></td>"
					+ "</tr>");
		}
	
		pw.println(	 		"</table>"
					+ 	"</body>"
					+"</html>");
		Details d1=new Details("A", new Date(), 5);
		System.out.println(d1.getName());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
