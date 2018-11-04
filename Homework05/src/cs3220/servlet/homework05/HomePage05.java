package cs3220.servlet.homework05;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.homework05.model.File;
import cs3220.servlet.homework05.model.User;

@WebServlet("/HomePage05")
public class HomePage05 extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null)
			response.sendRedirect("Login05");
		else {
			String id = request.getParameter("id");
			String sql = null;
			Connection c = null;
			List<File> arr = new ArrayList<>();
			try {
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
				String username = "cs3220stu03";
				String password = "*l3Aa4P5";

				c = DriverManager.getConnection(url, username, password);
				if (id == null)
					sql = "select * from files05 where owner_id= "+user.getId()+" and parent_id = 0 order by is_folder desc;";
				else{
					sql = "select * from files05 where owner_id= "+user.getId()+" and parent_id = "+id+" order by is_folder desc;";
					List<File> backList=new ArrayList<>();
					PreparedStatement pstmt=c.prepareStatement("select * from files05 where id = ? ;");
					pstmt.setInt(1, Integer.parseInt(id));
					ResultSet rs=pstmt.executeQuery();
					for (;rs.next();rs.close(),rs=pstmt.executeQuery()){
						backList.add(new File(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getLong("size"),
								rs.getTimestamp("date"), rs.getInt("parent_Id"), rs.getBoolean("is_Folder"),
								rs.getInt("owner_Id")));
						pstmt.setInt(1, rs.getInt("parent_Id"));
					}
					request.setAttribute("homework05BackList", backList);
				}
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next())
					arr.add(new File(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getLong("size"),
							rs.getTimestamp("date"), rs.getInt("parent_Id"), rs.getBoolean("is_Folder"),
							rs.getInt("owner_Id")));
				
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
			request.setAttribute("homework05List", arr);
			request.getRequestDispatcher("WEB-INF/homework05/jsps/HomePage05.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
