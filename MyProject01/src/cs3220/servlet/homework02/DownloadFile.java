package cs3220.servlet.homework02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadFile
 */
@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int currentFolderId=Integer.parseInt(request.getParameter("currentFolderId"));
		int parentFolderId=Integer.parseInt(request.getParameter("parentFolderId"));
		Map<Integer, Map<Integer,cs3220.servlet.homework02.model.File>> map=(Map<Integer, Map<Integer, cs3220.servlet.homework02.model.File>>) getServletContext().getAttribute("homework02.map");
        cs3220.servlet.homework02.model.File currentFile=map.get(parentFolderId).get(currentFolderId);

        // Get the path to the file and create a java.io.File object
        String path = getServletContext().getRealPath( "/WEB-INF/files/"+currentFile.getId());
        File file = new File( path );

        // Set the response headers. File.length() returns the size of the file
        // as a long, which we need to convert to a String.
        response.setContentType( currentFile.getType() );
        response.setHeader( "Content-Disposition","attachment; filename="+currentFile.getId()+"."+currentFile.getType());
        
        // Binary files need to read/written in bytes.
        FileInputStream in = new FileInputStream( file );
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
