package cs3220.servlet.homework06;

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

import org.json.JSONArray;
import org.json.JSONObject;

import cs3220.servlet.homework06.model.User;

@WebServlet("/ShareFile06")
public class ShareFile06 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		JSONArray jarray=new JSONArray();
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select username, id from users06 where username <> ?;");
			pstmt.setString(1,((User) request.getSession().getAttribute("user")).getName());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				jarray.put(new JSONObject().putOnce("id", rs.getInt("id")).put("username", rs.getString("username")));
			
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
		response.getWriter().print(jarray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select id from sharedfiles06 where file_id = ? and owner_id = ?");
			pstmt.setInt(1, Integer.parseInt(request.getParameter("fileId")));
			pstmt.setInt(2, Integer.parseInt(request.getParameter("userId")));
			
			if(pstmt.executeQuery().next())
				response.getWriter().print("File already shared with this user.");
			else{
				pstmt=c.prepareStatement("insert into sharedfiles06 (file_id, owner_id) values (?, ?);");
				pstmt.setInt(1, Integer.parseInt(request.getParameter("fileId")));
				pstmt.setInt(2, Integer.parseInt(request.getParameter("userId")));
				pstmt.executeUpdate();
				response.getWriter().print("File Shared Successfully");
			}
			
		} catch (SQLException e) {
			response.getWriter().print("Some Problem Occurred.");
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
