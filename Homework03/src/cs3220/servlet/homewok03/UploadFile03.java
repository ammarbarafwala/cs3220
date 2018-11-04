package cs3220.servlet.homewok03;

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

@WebServlet("/UploadFile03")
public class UploadFile03 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	request.getRequestDispatcher("/WEB-INF/UploadFile03.jsp").forward(request, response);
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	long parentFolderId=0, currentFolderId=0;
    	ServletContext servletContext = this.getServletConfig().getServletContext();
        @SuppressWarnings("unchecked")
		Map<Long, Map<Long,cs3220.servlet.homework03.model.File>> map=(Map<Long, Map<Long, cs3220.servlet.homework03.model.File>>) servletContext.getAttribute("homework03Map");
        int id=(int) servletContext.getAttribute("homework03Id");
        
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
	            map.get(currentFolderId).put((long) id, 
	            		new cs3220.servlet.homework03.model.File(id, fileName, 
	            				FilenameUtils.getExtension(fileName), file.length(), map.get(parentFolderId).get(currentFolderId), false));
            else{
            	Map<Long, cs3220.servlet.homework03.model.File> internalMap=new HashMap<>();
        		internalMap.put((long) id, new cs3220.servlet.homework03.model.File(id, fileName, 
        				FilenameUtils.getExtension(fileName), file.length(), map.get(parentFolderId).get(currentFolderId), false));
        		map.put(currentFolderId, internalMap);
            }
            servletContext.setAttribute("homework03Id", id);
        }
        catch( Exception e )
        {
            throw new IOException( e );
        }
        request.getRequestDispatcher("/WEB-INF/HomePage03.jsp?currentFolderId="+currentFolderId+"&parentFolderId="+parentFolderId).forward(request, response);
    }

}