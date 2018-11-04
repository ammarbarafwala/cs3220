package cs3220.servlet.lab15;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.lab15.model.Reservation;

/**
 * Servlet implementation class MakeReservation
 */
@WebServlet("/MakeReservation")
public class MakeReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/RoomReservations.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name").trim();
		if(name==null || name.equals(""))
			response.sendRedirect("MakeReservation");
		else{
			List<Reservation> list=(List<Reservation>)getServletContext().getAttribute("lab15List");
			String day=request.getParameter("selectedDay");
			String time=request.getParameter("selectedTime");
			for(Reservation r:list){
				if(r.getDay().equals(day) && r.getTime().equals(time)){
					request.getRequestDispatcher("/WEB-INF/ReservationError.jsp").forward(request, response);
					return;
				}
			}
			list.add(new Reservation(time, day, name));
			request.getRequestDispatcher("/WEB-INF/RoomReservations.jsp").forward(request, response);
		}
	}

}
