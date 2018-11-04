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

/**
 * Servlet implementation class FinalCreateCourseMapping
 */
@WebServlet("/FinalCreateCourseMapping")
public class FinalCreateCourseMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select * from quarterCourses q "
					+ "left join courseMappings c "
					+ "on q.id=c.quarterCourseId where c.id is null order by q.name asc;");
			ResultSet rs=pstmt.executeQuery();
			CourseDetails cd=new CourseDetails();
			while (rs.next())
				cd.getQuarterCourseList().add(rs.getString("name"));
			
			pstmt=c.prepareStatement("select * from semesterCourses q "
					+ "left join courseMappings c "
					+ "on q.id=c.semesterCourseId where c.id is null order by q.name asc;");
			 rs=pstmt.executeQuery();
			while (rs.next()){
				cd.getSemesterCourseList().add(rs.getString("name"));
				}
			
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
		request.getRequestDispatcher("/WEB-INF/finalLab/jsps/FinalCreateCourseMapping.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select id from quarterCourses where name = ?");
			System.out.println(request.getParameter("qCourse"));
			pstmt.setString(1, request.getParameter("qCourse"));
			ResultSet rs=pstmt.executeQuery();
			int qId=0,sId=0;
			if (rs.next())
				qId=rs.getInt("id");
			
			pstmt=c.prepareStatement("select id from semesterCourses where name = ?");
			System.out.println(request.getParameter("sCourse"));
			pstmt.setString(1, request.getParameter("sCourse"));
			rs=pstmt.executeQuery();
			if (rs.next())
				sId=rs.getInt("id");
			
			pstmt=c.prepareStatement("insert into courseMappings (quarterCourseId, semesterCourseId) values (?,?);");
			pstmt.setInt(1, qId);
			pstmt.setInt(2, sId);
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
		response.sendRedirect("FinalHomePage");
	}

}
