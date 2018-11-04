package cs3220.servlet.homework02;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		response.getWriter().print("<html>"
				+		"<head><title></title></head>"
				+ 		"<body>"
				+ 			"<form method='post' action='UploadFile' enctype='multipart/form-data'>"
				+ 				"File: <input type='file' name='name'/> "
				+				"<input type='hidden' name='currentFolderId' value='"+request.getParameter("currentFolderId")+""+"'>"
				+				"<input type='hidden' name='parentFolderId' value='"+request.getParameter("parentFolderId")+"'>"
				+ 				"<input type='submit' value='Upload'/>"
				+ 			"</form>"
				+ 		"</body>"
				+	"</html>");
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	int parentFolderId=0, currentFolderId=0;
    	ServletContext servletContext = this.getServletConfig().getServletContext();
        @SuppressWarnings("unchecked")
		Map<Integer, Map<Integer,cs3220.servlet.homework02.model.File>> map=(Map<Integer, Map<Integer, cs3220.servlet.homework02.model.File>>) servletContext.getAttribute("homework02.map");
        int id=(int) servletContext.getAttribute("homework02.id");
        
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
      
        File repository = (File) servletContext
            .getAttribute( "javax.servlet.context.tempdir" );
        factory.setRepository( repository );

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload( factory );

        // The directory we want to save the uploaded files to.
        String fileDir = getServletContext().getRealPath( "/WEB-INF/files" );

        // Parse the request
        try
        {
        	String fileName = null;
        	File file = null;
            List<FileItem> items = upload.parseRequest( request );
            for( FileItem item : items )
            {
                // If the item is not a form field - meaning it's an uploaded
                // file, we save it to the target dir
                if( !item.isFormField() )
                {
                    // item.getName() will return the full path of the uploaded
                    // file, e.g. "C:/My Documents/files/test.txt", but we only
                    // want the file name part, which is why we first create a
                    // File object, then use File.getName() to get the file
                    // name.
                    fileName = (new File( item.getName() )).getName();
                    file = new File( fileDir, ++id+"");
                    item.write( file );
                }
                else{
                	if(item.getFieldName().equals("parentFolderId"))
                		parentFolderId=Integer.parseInt(item.getString());
                	else if(item.getFieldName().equals("currentFolderId"))
                		currentFolderId=Integer.parseInt(item.getString());
                }
            }
            if( map.containsKey(currentFolderId))
	            map.get(currentFolderId).put(id, 
	            		new cs3220.servlet.homework02.model.File(id, fileName, 
	            				FilenameUtils.getExtension(fileName), file.length(), map.get(parentFolderId).get(currentFolderId), false));
            else{
            	Map<Integer, cs3220.servlet.homework02.model.File> internalMap=new HashMap<>();
        		internalMap.put(id, new cs3220.servlet.homework02.model.File(id, fileName, 
        				FilenameUtils.getExtension(fileName), file.length(), map.get(parentFolderId).get(currentFolderId), false));
        		map.put(currentFolderId, internalMap);
            }
            servletContext.setAttribute("homework02.id", id);
        }
        catch( Exception e )
        {
            throw new IOException( e );
        }
        if(currentFolderId!=0)
        	response.sendRedirect("HomePage?currentFolderId="+currentFolderId+"&parentFolderId="+parentFolderId);
        else
        	response.sendRedirect("HomePage");
        
    }

}