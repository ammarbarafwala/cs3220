package cs3220.servlet.homework05;

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

import cs3220.servlet.homework05.model.User;

/**
 * Servlet implementation class Login05
 */
@WebServlet("/Login05")
public class Login05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getSession().getAttribute("user") != null)
			response.sendRedirect("HomePage05");
		else {
			request.setAttribute("error", request.getParameter("error"));
			request.getRequestDispatcher("/WEB-INF/homework05/jsps/Login05.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("username");
		String pwd=request.getParameter("password");
		Connection c = null;
		boolean flag=false;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select * from users05 where username= ? and password= ? ;");
			pstmt.setString(1, user);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			if (flag=rs.next())
				request.getSession().setAttribute("user",new User(rs.getInt("id"), rs.getString("username")));
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if (flag)
			response.sendRedirect("HomePage05");
		else
			response.sendRedirect("Login05?error=Incorrect%20username/password.");
	}
}
