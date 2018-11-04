package cs3220.servlet.lab17;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloJDBC
 */
@WebServlet("/HelloJDBC")
public class HelloJDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloJDBC() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.println( "<!DOCTYPE html>" );
        out.println( "<html><head><title>Hello JDBC</title></head><body>" );

        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
            String username = "cs3220stu03";
            String password = "*l3Aa4P5";

            Connection c = DriverManager.getConnection( url, username,
                password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from items" );

            while( rs.next() )
            {
                out.println( rs.getString( "name" ) );
                out.println( rs.getString( "price" ) );
                out.println( rs.getFloat( "quantity" ) );
                out.println( "<br>" );
            }

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }

        out.print( "</body></html>" );
    }

}