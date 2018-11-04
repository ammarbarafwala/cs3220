package cs3220.servlet.homework06;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cs3220.servlet.homework06.model.User;

/**
 * Servlet implementation class NewFolder06
 */
@WebServlet("/NewFolder06")
public class NewFolder06 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null)
			response.sendRedirect("Login06");
		else
			request.getRequestDispatcher("/WEB-INF/homework06/jsps/NewFolder06.jsp").forward(request, response);
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
			PreparedStatement pstmt = c.prepareStatement("insert into files06 (name, parent_id, owner_id, date) values (?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, request.getParameter("folderName"));
			int parentId=0;
			if(id!=null)
				parentId=Integer.parseInt(id);
			pstmt.setInt(2, parentId);
			pstmt.setInt(3, ((User)request.getSession().getAttribute("user")).getId());
			Timestamp t=new Timestamp(new Date().getTime());
			pstmt.setTimestamp(4, t);
			pstmt.executeUpdate();
			ResultSet rs=pstmt.getGeneratedKeys();
			if(rs.next())
				response.getWriter().print(
						new JSONObject()
						.put("id", rs.getInt(1))
						.put("date", new SimpleDateFormat("MM/dd/yyyy hh:mm a").format(t))
						.put("parentId", parentId));
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
	}

}
