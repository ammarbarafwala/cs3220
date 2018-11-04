package cs3220.servlet.lab10;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("<html>"
				+ 						"<head><title></title></head>"
				+ 						"<body>"
				+ 							"<form method='post' action='Login'>"
				+ 								"Username: <input type='text' name='username'><br><br>"
				+ 								"Password: <input type='password' name='password'><br><br>"
				+ 								"<input type='submit' value='Login'>"
				+ 							"</form>"
				+ 						"</body>"
				+ 					"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(username.equals("cysun") && password.equals("abcd")){
			request.getSession().setAttribute("user", username);
			response.sendRedirect("Members");
		}
		else
			response.sendRedirect("Login");
	}

}
