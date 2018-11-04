package cs3220.servlet.contacts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.contacts.model.ContactList;

/**
 * Servlet implementation class ShowContact
 */
@WebServlet("/ShowContact")
public class ShowContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ContactList> list=new ArrayList<>();
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select * from contacts where name = ?");
			pstmt.setString(1, request.getParameter("name"));
			ResultSet rs=pstmt.executeQuery();
			ResultSetMetaData md=rs.getMetaData();
			int columnCount=md.getColumnCount();
			if(rs.next()){
				request.setAttribute("name", rs.getString("name"));
				for(int i=1;i<=columnCount;i++){
					String name=md.getColumnName(i);
					list.add(new ContactList(name, rs.getString(name)));
				}
			}
			request.setAttribute("contactsList", list);
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
		
		request.getRequestDispatcher("WEB-INF/contacts/jsps/ShowContact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			String cName=request.getParameter("cName");
			PreparedStatement pstmt=c.prepareStatement("alter table contacts add "+cName+" varchar(255);");
			pstmt.executeUpdate();
			System.out.println(name+" "+cName);
			pstmt=c.prepareStatement("update contacts set "+cName+" = ? where Name = ? ;");
			pstmt.setString(1, request.getParameter("value"));
			pstmt.setString(2, name);
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
		response.sendRedirect("ShowContact?name="+name);
	}

}
