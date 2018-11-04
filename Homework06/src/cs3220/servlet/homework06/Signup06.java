package cs3220.servlet.homework06;

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

/**
 * Servlet implementation class Signup06
 */
@WebServlet("/Signup06")
public class Signup06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("error", request.getParameter("error"));
		request.getRequestDispatcher("/WEB-INF/homework06/jsps/Signup06.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("username").trim();
		String pwd=request.getParameter("password").trim();
		Connection c = null;
		boolean flag=false;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			if (flag=(pwd!=null && !pwd.isEmpty() && user!=null && !user.isEmpty())) {
				PreparedStatement pstmt=c.prepareStatement("insert into users06 (username, password) values (?, ?);");
				pstmt.setString(1, user);
				pstmt.setString(2, pwd);
				pstmt.executeUpdate();
			}
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
			response.sendRedirect("HomePage06");
		else
			response.sendRedirect("Signup06?error=Username%20and%20Password%20cannot%20be%20empty.");	
	}
}
