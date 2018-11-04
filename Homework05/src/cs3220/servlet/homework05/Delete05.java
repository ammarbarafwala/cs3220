package cs3220.servlet.homework05;

import java.io.File;
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

@WebServlet("/Delete05")
public class Delete05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		String parentId = request.getParameter("parentId");
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			if(Boolean.parseBoolean(request.getParameter("isFolder")))
				delete(c,id);
			else
				new File( getServletContext().getRealPath( "/WEB-INF/files/"+id) ).delete();
			PreparedStatement pstmt=c.prepareStatement("delete from files05 where id = ?;");
			pstmt.setInt(1, id);
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
        response.sendRedirect("HomePage05?id="+parentId);
	}

	//deleting All sub-folders
	private void delete(Connection c, int id) throws SQLException{
		PreparedStatement pstmt=c.prepareStatement("select id, is_folder from files05 where parent_id = ?;");
		pstmt.setInt(1, id);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			pstmt=c.prepareStatement("delete from files05 where id = ?;");
			pstmt.setInt(1, rs.getInt("id"));
			pstmt.executeUpdate();
			if(rs.getBoolean("is_folder"))
				delete(c, rs.getInt("id"));
			else
				new File( getServletContext().getRealPath( "/WEB-INF/files/"+rs.getInt("id")) ).delete();
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
