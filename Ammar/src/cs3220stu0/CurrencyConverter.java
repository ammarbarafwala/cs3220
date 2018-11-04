package cs3220stu0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.CurrencyRate;

/**
 * Servlet implementation class CurrencyConverter
 */
@WebServlet("/CurrencyConverter")
public class CurrencyConverter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		List<CurrencyRate> list=new ArrayList<CurrencyRate>();
		try {
			Scanner sc=new Scanner(new File(getServletContext().getRealPath("/WEB-INF/rates.txt")));
			while(sc.hasNextLine()){
				String[] s=sc.nextLine().split(" ");
				list.add(new CurrencyRate(s[0], Double.parseDouble(s[1])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		getServletContext().setAttribute("dataList", list);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CurrencyRate> list=(List<CurrencyRate>) getServletContext().getAttribute("dataList");
		PrintWriter out=response.getWriter();
		out.println(" <html>"
					+ "<head><title></title></head>"
					+ "<body'>"
					+ "<form action='CurrencyConverter' method='post'>"
						+ "<input type='text' name='value'/> "
						+ "<select name='from'>");
		
		for(int i=0;i<list.size();i++){
			CurrencyRate cr=list.get(i);
			out.println("<option value='"+i+"'>"+ cr.getName() + "</option>");
		}
		out.println("</select> =? "
					+ "<select name='to'>");
		for(int i=0;i<list.size();i++){
			CurrencyRate cr=list.get(i);
			out.println("<option value='"+i+"'>"+ cr.getName() + "</option>");
		}
		out.println( 	"</select> "
						+ "<input type='submit' value='Convert'>"
					+ "</form>"
					+ "</body>"
					+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int from=Integer.parseInt(request.getParameter("from"));
		int to=Integer.parseInt(request.getParameter("to"));
		double value=Double.parseDouble(request.getParameter("value"));
		List<CurrencyRate> list=(List<CurrencyRate>)getServletContext().getAttribute("dataList");
		double fromRate=list.get(from).getRate();
		double toRate=list.get(to).getRate();
		double result=(value/fromRate)*toRate;
		PrintWriter out=response.getWriter();
		
		out.println("<html>"
				+"<body><p>"
				+ value+" "+list.get(to).getName()+" = ");
		out.printf("%.2f", +result);
		
		out.println(" "+list.get(from).getName()+""		
				+ "</p>"
				+ "<a href='CurrencyConverter'></a>"
				+ "</body>"
			+"</html>");
		//response.sendRedirect("Result?result="+result+"&&resultType="+list.get(from).getName()+"&&value="+value+"&&valueType="+list.get(to).getName());
	}

}
