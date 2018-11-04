package cs3220.servlet.homework05;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

import cs3220.servlet.homework05.model.User;

@WebServlet("/UploadFile05")
public class UploadFile05 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null)
			response.sendRedirect("Login05");
		else
			request.getRequestDispatcher("/WEB-INF/homework05/jsps/UploadFile05.jsp").forward(request, response);
	}

	protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	String id=null;
    	ServletContext servletContext = this.getServletConfig().getServletContext();
	
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
        Connection c = null;
        try
        {
        	String fileName = null;
        	File file = null;
            List<FileItem> items = upload.parseRequest( request );
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt= c.prepareStatement("select max(id) from files05;");
			ResultSet rs=pstmt.executeQuery();
			int maxId=0;
			if(rs.next())
				maxId=rs.getInt(1);
			System.out.println(maxId);
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
                    file = new File( fileDir, ++maxId+"");
                    item.write( file );
                }
                else{
                	if(item.getFieldName().equals("id"))
                		id=item.getString();
                }
            }
            pstmt=c.prepareStatement("insert into files05 (id, name, type, size, is_folder, parent_id, owner_id) values (?,?,?,?,?,?,?);");
            pstmt.setInt(1, maxId);
            pstmt.setString(2, fileName);
            pstmt.setString(3, FilenameUtils.getExtension(fileName));
            pstmt.setLong(4, file.length());
            pstmt.setBoolean(5, false);
            if(id==null)
            	pstmt.setInt(6, 0);
            else
            	pstmt.setInt(6, Integer.parseInt(id));
            pstmt.setInt(7, ((User)request.getSession().getAttribute("user")).getId());
            pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
        catch( Exception e )
        {
            throw new IOException( e );
        }
        finally
        {
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
        response.sendRedirect("HomePage05?id="+id);
    }

}