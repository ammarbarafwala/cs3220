package cs3220.servlet.homework05;

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

import cs3220.servlet.homework05.model.User;

/**
 * Servlet implementation class NewFolder05
 */
@WebServlet("/NewFolder05")
public class NewFolder05 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null)
			response.sendRedirect("Login05");
		else
			request.getRequestDispatcher("/WEB-INF/homework05/jsps/NewFolder05.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement("insert into files05 (name, parent_id, owner_id) values (?,?,?);");
			pstmt.setString(1, request.getParameter("name"));
			if(id==null)
				pstmt.setInt(2, 0);
			else
				pstmt.setInt(2, Integer.parseInt(id));
			pstmt.setInt(3, ((User)request.getSession().getAttribute("user")).getId());
			pstmt.executeUpdate();
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
		response.sendRedirect("HomePage05?id="+request.getParameter("id"));
	}

}
