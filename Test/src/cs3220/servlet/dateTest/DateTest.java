package cs3220.servlet.dateTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DateTest
 */
@WebServlet("/DateTest")
public class DateTest extends HttpServlet {
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
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu03";
			String username = "cs3220stu03";
			String password = "*l3Aa4P5";

			c = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt=c.prepareStatement("select * from files05 where name='Doc';");
			ResultSet rs = pstmt.executeQuery();
			Date date;
			while (rs.next()) {
				date= rs.getTimestamp("date");
				request.setAttribute("date", date);
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
		response.getWriter().println("<html>"
				+ "<head>"
				+ "<link rel='stylesheet' type='text/css' href='/style/style.css'>"
						+ "<meta charset='ISO-8859-1'>"
						+ "<title>Insert title here</title>"
						+ "	</head>"
						+ "	<body>"
						+ "		<h2>Upload File</h2>"
						+ "		<form method='post' action='UploadFile04' enctype='multipart/form-data'>"
						+ "			<p>File: <input type='file' name='name'/></p>"
						+ "			<input type='hidden' name='currentFolderId' value='${param.currentFolderId}'>"
						+ "			<input type='hidden' name='parentFolderId' value='${param.parentFolderId}'>"
						+ "			<input type='submit' value='Upload'/>"
						+ "			</form>"
						+ "	</body>"
						+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
