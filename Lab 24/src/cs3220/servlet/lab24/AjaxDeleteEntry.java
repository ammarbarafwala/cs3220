package cs3220.servlet.lab24;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjaxDeleteEntry")
public class AjaxDeleteEntry extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AjaxDeleteEntry()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        Connection c = null;
        try
        {
        	 String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
             String username = "cs3220stu03";
             String password = "*l3Aa4P5";

            String sql = "delete from guestbook where id = ?";

            c = DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt( 1, Integer.parseInt( request.getParameter( "id" ) ) );
            pstmt.executeUpdate();

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        doGet( request, response );
    }

}
