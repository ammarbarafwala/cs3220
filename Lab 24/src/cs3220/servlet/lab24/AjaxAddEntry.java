package cs3220.servlet.lab24;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

@WebServlet("/AjaxAddEntry")
public class AjaxAddEntry extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AjaxAddEntry()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        int id = 0;

        Connection c = null;
        try
        {
        	 String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
             String username = "cs3220stu03";
             String password = "*l3Aa4P5";

            String sql = "insert into guestbook (name, message, date) values (?, ?, now())";

            c = DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt = c.prepareStatement( sql,
                Statement.RETURN_GENERATED_KEYS );
            pstmt.setString( 1, request.getParameter( "name" ) );
            pstmt.setString( 2, request.getParameter( "message" ) );
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if( rs.next() ) id = rs.getInt( 1 );
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

        response.setContentType( "text/plain" );
        response.getWriter().print( id );
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        doGet( request, response );
    }

}
