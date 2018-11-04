package cs3220.servlet.lab20;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

import cs3220.servlet.lab20.model.Department;
import cs3220.servlet.lab20.model.Faculty;

@WebServlet(urlPatterns = "/DisplayFaculty", loadOnStartup = 1)
public class DisplayFaculty extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DisplayFaculty() {
		super();
	}

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
			
			for(Department d:departmentsList){
				List<Faculty> facultiesList = d.getFaculty();
				ResultSet rs2 = stmt.executeQuery(
						"select f.* from faculties f inner join departments d on f.department_id=d.id where d.name='"+d.getName()+"';");
				while(rs2.next()){
					facultiesList.add(new Faculty(rs2.getString("name"), rs2.getBoolean("is_Chair"), rs2.getInt("id"), rs2.getInt("department_id")));
				}
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
		request.setAttribute("departments", departmentsList);
		request.getRequestDispatcher("/WEB-INF/lab20Jsp/DisplayFaculty.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}