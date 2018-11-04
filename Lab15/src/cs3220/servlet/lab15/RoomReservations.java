package cs3220.servlet.lab15;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.lab15.model.Reservation;

/**
 * Servlet implementation class RoomReservations
 */
@WebServlet("/RoomReservations")
public class RoomReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    public void init() throws ServletException {
    	List<Reservation> list=new ArrayList<>();
    	String days[]={"MON","TUE","WED","THR","FRI"};
    	String times[]={"08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00",
    							"16:00-17:00"};
    	list.add(new Reservation(times[1],days[1], "Kang"));
    	list.add(new Reservation(times[2], days[0], "Pamula"));
    	list.add(new Reservation(times[5], days[2], "Abbott"));
    	list.add(new Reservation(times[6], days[3], "Sun"));
    	ServletContext context=getServletContext();
    	context.setAttribute("lab15List", list);
    	context.setAttribute("lab15Days", days);
    	context.setAttribute("lab15Times", times);
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/RoomReservations.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
