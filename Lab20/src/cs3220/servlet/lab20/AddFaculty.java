package cs3220.servlet.lab20;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.lab20.model.Department;
import cs3220.servlet.lab20.model.Faculty;

@WebServlet("/AddFaculty")
public class AddFaculty extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddFaculty() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = null;
		List<Department> departmentsList = new ArrayList<>();
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from departments");
			
			while (rs.next())
				departmentsList.add(new Department(rs.getString("name")));
			
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
		request.setAttribute("departments", departmentsList);
        
		request.getRequestDispatcher("/WEB-INF/lab20Jsp/AddFaculty.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String departmentName = request.getParameter("department");
		boolean isChair=false;
		if( request.getParameter( "chair" ) != null ) isChair=true ;

		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT id from departments WHERE name='"+departmentName+"';");
			rs.next();
			stmt.executeUpdate("insert into faculties (name,is_chair,department_id) values ('"
					+ request.getParameter("faculty") + "',"+isChair+","+rs.getInt("id")+");");

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