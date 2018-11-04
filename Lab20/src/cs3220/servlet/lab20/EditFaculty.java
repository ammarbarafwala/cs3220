package cs3220.servlet.lab20;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.lab20.model.Department;
import cs3220.servlet.lab20.model.Faculty;

/**
 * Servlet implementation class EditFaculty
 */
@WebServlet("/EditFaculty")
public class EditFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditFaculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from faculties WHERE id=" + request.getParameter("id") + ";");
			rs.next();
			Faculty f = new Faculty(rs.getString("name"), rs.getBoolean("is_chair"), rs.getInt("id"),
					rs.getInt("department_id"));
			request.setAttribute("faculty", f);
			ArrayList<Department> departments = new ArrayList<>();
			rs = stmt.executeQuery("select * from departments;");
			while (rs.next()) {
				if (f.getDepartmentId() == rs.getInt("id")) {
					System.out.println("hi");
					request.setAttribute("departmentName", rs.getString("name"));
				}
				departments.add(new Department(rs.getString("name")));
			}
			request.setAttribute("departments", departments);

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

		request.getRequestDispatcher("/WEB-INF/lab20Jsp/EditFaculty.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String departmentName = request.getParameter("department");
		boolean isChair = false;
		if (request.getParameter("chair") != null)
			isChair = true;

		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id from departments WHERE name='" + departmentName + "';");
			rs.next();
			stmt.executeUpdate("UPDATE faculties SET name = '" + request.getParameter("faculty") + "', is_chair = "
					+ isChair + ", department_id = " + rs.getInt("id") + " where id = "+request.getParameter("id")+";");

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

		response.sendRedirect("DisplayFaculty");
	}

}
