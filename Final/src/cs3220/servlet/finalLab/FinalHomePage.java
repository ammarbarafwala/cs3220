package cs3220.servlet.finalLab;

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

import cs3220.servlet.finalLab.model.CourseDetails;
import cs3220.servlet.finalLab.model.CourseMapping;

@WebServlet("/FinalHomePage")
public class FinalHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select name from quarterCourses order by name asc;");
			ResultSet rs=pstmt.executeQuery();
			CourseDetails cd=new CourseDetails();
			while (rs.next())
				cd.getQuarterCourseList().add(rs.getString("name"));
			
			pstmt=c.prepareStatement("select name from semesterCourses order by name asc;");
			 rs=pstmt.executeQuery();
			while (rs.next())
				cd.getSemesterCourseList().add(rs.getString("name"));
			
			pstmt=c.prepareStatement("select q.name, s.name from quarterCourses q "
					+ "inner join semesterCourses s "
					+ "inner join courseMappings c "
					+ "on s.id=c.semesterCourseId "
					+ "and q.id=c.quarterCourseId order by q.name asc;");
			rs=pstmt.executeQuery();
			while (rs.next())
				cd.getCourseMapping().add(new CourseMapping(rs.getString(1), rs.getString(2)));
			
			request.setAttribute("finalObject", cd);
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
		request.getRequestDispatcher("/WEB-INF/finalLab/jsps/FinalHomePage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
