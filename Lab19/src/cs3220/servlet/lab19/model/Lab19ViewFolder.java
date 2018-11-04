package cs3220.servlet.lab19.model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lab19ViewFolder
 */
@WebServlet("/Lab19ViewFolder")
public class Lab19ViewFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection c = null;
		String id = request.getParameter("id");
		ArrayList<Lab19File> arr = new ArrayList<>();
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			String sql = null;

			if (id == null)
				sql = "select * from files f1 inner join users u on f1.owner_id = u.id where u.name='cysun' and f1.parent_id is null order by f1.is_folder desc";
			else
				sql = "select * from files f1 inner join users u on f1.owner_id = u.id where u.name='cysun' and f1.parent_id="
						+ id+" order by f1.is_folder desc";
			System.out.println(sql);
			
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				arr.add(new Lab19File(rs.getInt("id"), rs.getInt("owner_id"), rs.getInt("parent_id"),
						rs.getString("name"), rs.getBoolean("is_folder")));
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
		request.setAttribute("lab19List", arr);
		request.getRequestDispatcher("/WEB-INF/Lab19Jsp/Lab19ViewFolder.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
